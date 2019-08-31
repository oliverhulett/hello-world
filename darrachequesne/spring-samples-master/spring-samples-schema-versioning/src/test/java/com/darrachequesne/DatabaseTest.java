package com.darrachequesne;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DatabaseTest {

	private static final int VERSION = 2;
	private static final String SQL_DIR = "src/main/resources/sql";

	@Autowired
	private DataSource dataSource;

	@Test
	public void databaseEntitiesAgainstSchema()
			throws SQLException, IOException {
		Dialect dialect = new MySQL5Dialect();
		int nextVersion = DatabaseTest.VERSION + 1;

		String currentSchema = String.format("%s/schema_v%d.sql",
				DatabaseTest.SQL_DIR, DatabaseTest.VERSION);
		String nextSchema = String.format("%s/schema_v%d.sql",
				DatabaseTest.SQL_DIR, nextVersion);
		String migrationScript = String.format("%s/schema_v%d_to_v%d.sql",
				DatabaseTest.SQL_DIR, DatabaseTest.VERSION, nextVersion);

		ScriptUtils.executeSqlScript(dataSource.getConnection(),
				new FileSystemResource(currentSchema));

		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(
				dataSource);
		sessionFactory.scanPackages(this.getClass().getPackage().getName());

		List<SchemaUpdateScript> updateScripts = sessionFactory
				.generateSchemaUpdateScriptList(dialect, new DatabaseMetadata(
						dataSource.getConnection(), dialect, sessionFactory));
		if (!updateScripts.isEmpty()) {
			Formatter formatter = FormatStyle.DDL.getFormatter();

			PrintWriter migrationScriptWriter = new PrintWriter(
					new FileWriter(migrationScript));

			migrationScriptWriter.println(String.format("/* v%d to v%d */",
					DatabaseTest.VERSION, nextVersion));
			migrationScriptWriter.println();
			migrationScriptWriter.println(String.format(
					"UPDATE metadata SET value = %d WHERE property = 'VERSION';",
					nextVersion));
			migrationScriptWriter.println(
					"UPDATE metadata SET value = NOW() WHERE property = 'update_date';");
			migrationScriptWriter.println();

			for (SchemaUpdateScript script : updateScripts) {
				migrationScriptWriter
						.println(formatter.format(script.getScript()) + ";");
			}
			migrationScriptWriter.close();

			ScriptUtils.executeSqlScript(dataSource.getConnection(),
					new FileSystemResource(migrationScript));

			List<SchemaUpdateScript> creationScripts = new ArrayList<>();
			for (String s : sessionFactory
					.generateSchemaCreationScript(dialect)) {
				creationScripts.add(new SchemaUpdateScript(s, false));
			}

			PrintWriter nextSchemaWriter = new PrintWriter(
					new FileWriter(nextSchema));

			nextSchemaWriter.println(String.format("/* v%d */", nextVersion));
			nextSchemaWriter.println();
			nextSchemaWriter.println("drop database if exists sample;");
			nextSchemaWriter.println("create database sample;");
			nextSchemaWriter.println("use sample;");
			nextSchemaWriter.println();
			nextSchemaWriter.println("create table metadata ("
					+ System.lineSeparator()
					+ "    property varchar(255) not null,"
					+ System.lineSeparator()
					+ "    value varchar(255) not null,"
					+ System.lineSeparator()
					+ "    constraint pk_metadata primary key (property)"
					+ System.lineSeparator() + ");");
			nextSchemaWriter.println();
			nextSchemaWriter.println(String.format(
					"insert into metadata values ('version', %d), ('update_date', NOW());",
					nextVersion));
			nextSchemaWriter.println();

			for (SchemaUpdateScript script : creationScripts) {
				nextSchemaWriter
						.println(formatter.format(script.getScript()) + ";");
			}
			nextSchemaWriter.close();
		}
		// Throws on error.
		sessionFactory.validateSchema(dialect, new DatabaseMetadata(
				dataSource.getConnection(), dialect, sessionFactory));
	}
}
