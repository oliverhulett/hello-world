/* v2 to v3 */

UPDATE metadata SET value = 3 WHERE property = 'VERSION';
UPDATE metadata SET value = NOW() WHERE property = 'update_date';


    alter table task 
        add column subtasks_id char(36);

    alter table task 
        add constraint FK_5m1vudisnedt2xxkhccnwtai0 
        foreign key (subtasks_id) 
        references task (id);
