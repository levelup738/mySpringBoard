# Spring 게시판 예제 

spring으로 간단한 게시판은 만들어 보았습니다.

## 개발 환경

- Front

  HTML, CSS, Vanilla JS

- Back

  Spring MVC Project

  Mybatis

  JDK

  MariaDB

  Tomcat

  Maven

## 제작 기록

[초기버전 제작기간 (2020-09-07 ~ 2020-09-23)](/Diary/initsetting.md)

## 주요 구현 기능

- [회원가입시 이메일 인증링크를 클릭해야만 회원가입 완료가 됨](/Diary/emailCheck.md)
- [본인이 작성한 게시글/댓글만 수정, 삭제 가능](/Diary/modifyAndDelete.md)
- [ajax로 ID중복확인](/Diary/idDoubleCheck.md)
- [동적SQL을 이용한 검색기능](/Diary/dynamicSQL.md)
- [logininterceptor기능 으로 로그인 안하고 게시글 보려고 하면 로그인 페이지로 보냄](/Diary/loginInterceptor.md)

## DB Table

![UML](https://user-images.githubusercontent.com/47135267/97111652-69b9a380-1723-11eb-8f45-fda8b6125977.png)



각 regdate에는 DEFAULT NOW()를 적용해서 자동으로 등록시간이 들어가게 만들었습니다.

c_boardseq는 b_seq를 참조합니다.

ON DELETE CASCADE를 적용하여서 글이 지워지면 댓글도 같이 삭제되도록 하였습니다.

