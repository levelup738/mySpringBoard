## 이메일 인증 기능

![email1](https://user-images.githubusercontent.com/47135267/97111927-e600b680-1724-11eb-8a4b-18d5864a4f86.PNG)

email을 보내기 위한 셋팅

![email2](https://user-images.githubusercontent.com/47135267/97111942-f6b12c80-1724-11eb-8721-c73ed868ea3a.PNG)

유저 회원가입 진행

![email3](https://user-images.githubusercontent.com/47135267/97111955-06c90c00-1725-11eb-96f4-3ec82aa7718e.PNG)

가입요청을 하면 이메일을 보냅니다.

그리고 @Transactional을 이용하여서 유저정보를 삽입하는데 문제가 생기면 이메일을 전송하지 않도록 하였습니다.

![email4](https://user-images.githubusercontent.com/47135267/97112069-a25a7c80-1725-11eb-8859-31bab46a84f4.PNG)

이메일이 전송된다면 이런식으로 링크가 가게됩니다.

![email5캡처](https://user-images.githubusercontent.com/47135267/97112084-b3a38900-1725-11eb-8a0a-a9e44837e0dd.PNG)

가입 이메일링크를 클릭하면 DB에서 user정보의 authstatus를 1로 바꿉니다. 

![email6](https://user-images.githubusercontent.com/47135267/97112116-da61bf80-1725-11eb-8174-7a3453a8f9f1.PNG)

가입 완료가 된 모습



여담) @Transactional의 rollbackfor 가 무엇을 하느냐

Exception에는 CheckedException, unCheckedException이 있다.

|                            |     CheckedException      |               unCheckedException               |
| :------------------------: | :-----------------------: | :--------------------------------------------: |
|       **처리 여부**        |     반드시 처리해야함     |            예외 처리 하지 않아도됨             |
| **트랜잭션 Rollback 여부** |       Rollback 안됨       |                 Rollback 진행                  |
|     **대표 Exception**     | IOException, SQLException | NullPointerException, IllegalArgumentException |

간단하게 표로 보면 이렇다.

그래서 rollbackfor 속성을 주면 rollback이 된다.

