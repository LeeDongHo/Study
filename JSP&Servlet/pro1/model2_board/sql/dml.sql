insert into MEMBER(ID,PASSWORD,NAME,EMAIL)
values('admin','asdf11','관리자','admin@gamil.com');
insert into MEMBER(ID,PASSWORD,NAME,EMAIL)
values('asdf','asdf11','테스터1','admin@gamil.com');
insert into MEMBER(ID,PASSWORD,NAME,EMAIL)
values('kim','asdf11','테스터2','admin@gamil.com');
insert into MEMBER(ID,PASSWORD,NAME,EMAIL)
values('lee','asdf11','테스터2','admin@gamil.com');

insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(0,'테스트1','배가 부르다',null,sysdate,'test03');
insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(0,'테스트2','살 빼야하는데',null,sysdate,'lee');
insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(2,'답글1','운동은 시름',null,sysdate,'admin');
insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(3,'답글2','너우무시름',null,sysdate,'admin');
insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(0,'테스트3','공부도 해야한다',null,sysdate,'kim');
insert into BOARD(PARENTID,TITLE,CONTENT,IMAGEFILENAME,WRITEDDATE,ID)
values(2,'ㅎㅇㅎㅇ','ㅂㅇㅂㅇ',null,sysdate,'asdf');


commit;