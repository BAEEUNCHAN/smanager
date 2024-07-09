CREATE table tbl_student(
    std_no VARCHAR2(10) PRIMARY KEY,
    std_name VARCHAR2(100) not null,
    std_phone VARCHAR2(20),
    address VARCHAR2(100),
    birth_date date,
    creation_date date DEFAULT sysdate
    );
    
INSERT into tbl_student (std_no, std_name, std_phone)
VALUES ('s2024-01', 'È«±æµ¿', '010-1234-5678');

INSERT into tbl_student (std_no, std_name, std_phone, address)
VALUES ('s2024-02', '±è±æµ¿', '010-3333-4444', '¼­¿ï 100¹øÁö');

INSERT into tbl_student (std_no, std_name, std_phone)
VALUES ('s2024-03', 'ÀÌÃ¢È£', '010-5555-7777');

SELECT * 
from tbl_student;

UPDATE tbl_student
SET address = '´ëÀü 100'
WHERE std_no = 's2024-01';

select *
from tbl_student;

insert into tbl_student(std_no, std_name, std_phone)
values('s2024-04','±è¹Î±Ô','010-2222-5678');

commit;

INSERT into tbl_student (std_no, std_name, std_phone)
VALUES ('s1996-11', 'ÀÌ¿ì»ó', '010-7777-7777');

commit;

insert into tbl_student(std_no, std_name, std_phone)
values ('s1992-05','±è°¨Ä¡','010-1234-5678');


commit;

select *
from tbl_student;
commit;
commit;





