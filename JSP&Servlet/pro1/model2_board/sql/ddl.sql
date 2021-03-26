DROP TABLE board CASCADE CONSTRAINTS;

CREATE TABLE board(
    articleId number generated as identity,
    parentId number(10) default 0,
    title varchar2(500) not null ,
    content varchar2(4000),
    imageFileName varchar2(30),
    writedDate date default sysdate not null,
    id varchar2(10),
    CONSTRAINT id FOREIGN KEY(id) REFERENCES MEMBER(id)
);

commit;