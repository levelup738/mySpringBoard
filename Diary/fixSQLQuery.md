## 이전글 다음글 불러오는 쿼리를 잘못짜서 수정

게시글 보기에서 밑에 이전글 다음글 링크거는게 있는데 

이 글번호를 불러오는 쿼리를 

```
SELECT b_seq, ... FROM tb_board WHERE IN (b_seq - 1, b_seq, b_seq + 1)
```

머 이런식으로 짯다. 근데 글을 삭제하고 수정하고 하다보면 이게 숫자가 안이어질수도 있는데 그걸 생각못했다.

```
SELECT b_seq, b_title, b_writer, b_hit, b_regdate, b_content
,(SELECT b_title FROM tb_board WHERE b_seq &lt; #{seq} ORDER BY b_seq DESC LIMIT 1) AS PREV_TITLE
,(SELECT b_title FROM tb_board WHERE b_seq &gt; #{seq} ORDER BY b_seq LIMIT 1) AS NEXT_TITLE
,(SELECT b_seq FROM tb_board WHERE b_seq &lt; #{seq} ORDER BY b_seq DESC LIMIT 1) AS PREV_SEQ
,(SELECT b_seq FROM tb_board WHERE b_seq &gt; #{seq} ORDER BY b_seq LIMIT 1) AS NEXT_SEQ
FROM tb_board WHERE b_seq = #{seq};
```

그래서 인터넷 찾아보면서 이렇게 짯는데 음.. 기능구현은 했는데

좀 무식해 보이는거같다..



여담) "&lt" "&gt" 로 한 이유?

그냥 < 이렇게 쓰면 올바르지 않은 XML양식처럼되서 에러난다.

```
<![CDATA[ 

SELECT * FROM table WHERE salary > 100 

]]>
```

이런식으로 CDATA로 묶어도 된다.