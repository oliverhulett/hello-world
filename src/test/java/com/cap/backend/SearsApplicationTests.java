package com.cap.backend;

import javax.sql.DataSource;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgresPlusDialect;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SearsApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
		// Doesn't fail if postgres isn't there, so hibernate testing can't be
		// working (also won't fail)
		// fix that...

		// Copied from hello-world, `DatabaseMetadata` doesn't exist in this
		// project? (Actually hbm2ddl doesn't exist)
		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(
				dataSource);
		Dialect dialect = new PostgresPlusDialect();
		DatabaseMetadata metadata = new DatabaseMetadata(
				dataSource.getConnection(), dialect, sessionFactory);
		sessionFactory.validateSchema(dialect, metadata);
	}
}
