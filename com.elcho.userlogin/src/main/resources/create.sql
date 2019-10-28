
drop table  if exists TBL_USER;
create table TBL_USER
(
    ID        INTEGER  auto_increment
        unique,
    NAME      CHAR,
    ROLE      CHAR,
    USER_NAME CHAR,
    PASSWORD  CHAR
);
