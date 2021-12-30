127.0.0.1:1521 SID:XE
SYS as SYSDBA/123456

-- CREATING TABLESPACE AND USER

CREATE TABLESPACE tbs_partitioning_demo_01
DATAFILE '/opt/oracle/oradata/XE/tbs_partitioning_demo_01.dbf'
SIZE 20M
AUTOEXTEND ON NEXT 5M
MAXSIZE 50M;

SELECT * FROM "V_$TABLESPACE" vt ;

alter session set "_ORACLE_SCRIPT"=true;

CREATE USER partitioning_usr
IDENTIFIED BY 123456
DEFAULT TABLESPACE tbs_partitioning_demo_01
quota unlimited ON tbs_partitioning_demo_01;

COMMIT;

GRANT ALL PRIVILEGES TO partitioning_usr;
GRANT CREATE SESSION TO partitioning_usr;

-- CREATING TABLE AND PARTITION FOR SCORE ATTRIBUTE;

CREATE TABLE PART_DEMO ( 
	SCORE NUMBER 
)
PARTITION BY RANGE (SCORE)
INTERVAL (10)
(PARTITION PART_SCORE_01 VALUES LESS THAN (10) ) ENABLE ROW MOVEMENT;

BEGIN
	FOR i in 1..30 LOOP
		INSERT INTO PART_DEMO(SCORE) VALUES ( i );
	END LOOP;
END;
COMMIT;

SELECT * FROM PART_DEMO PARTITION (PART_SCORE_01);

TRUNCATE TABLE PART_DEMO;

SELECT * FROM PART_DEMO pd;


-- DROP TABLESPACE tbs_partitioning_demo_01 INCLUDING CONTENTS AND DATAFILES;
