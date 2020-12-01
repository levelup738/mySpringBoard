## ajax를 활용해서 댓글창 만들기

Controller 부분은 목록 구성하는 부분만 예시로 남기겠습니다.

```java
@RequestMapping(value = "/comment/list.do", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<Object> comment_list(
			@RequestParam(value = "curPage", required = true, defaultValue = "1") Integer curPage,
			@RequestParam(value = "b_seq", required = true) Integer b_seq) {
		// JSON으로 데이터 만들어서 보내기 위해
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		// 페이지 정보 만들기
		PageMaker pageMaker = new PageMaker(curPage, 5);
		int totalPost = commentService.cntTotal(b_seq);
		PageInfo pageInfo = pageMaker.pageSetting(totalPost);
		// 댓글 리스트 뽑기
		List<CommentVO> commentVOs = commentService.setCommentList(pageMaker, b_seq);

		if(!commentVOs.isEmpty()) {
			for(int i = 0; i < commentVOs.size(); i++){
				JSONObject entity = new JSONObject();
				entity.put("c_seq", commentVOs.get(i).getSeq());
				entity.put("c_writer", commentVOs.get(i).getWriter());
				entity.put("c_content", commentVOs.get(i).getContent());
				// 날짜 변환
				DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("yy.MM.dd hh:mm");
				String strDate = commentVOs.get(i).getRegdate().format(dateTimeFmt);
				entity.put("c_regdate", strDate);
				// jsonList에 정보 넣기
				jsonList.add(entity);
			}
		}
		// 페이지정보 JSON으로 만들어서 넣기
		JSONObject pageInfoJson = new JSONObject();
		pageInfoJson.put("totalPage", pageInfo.getTotalPage());
		pageInfoJson.put("totalPost", pageInfo.getTotalPost());
		pageInfoJson.put("curPage", pageInfo.getCurPage());
		pageInfoJson.put("startPage", pageInfo.getStartPage());
		pageInfoJson.put("endPage", pageInfo.getEndPage());
		jsonList.add(pageInfoJson);

		return new ResponseEntity<Object>(jsonList, new HttpHeaders(), HttpStatus.CREATED);
	}
```

댓글 목록 생성하기

```javascript
const getCommentList = function (b_seq, curPage) {
    // 현재페이지 게시글번호 설정, 없으면 미리정해둔값으로 설정
    let mySeq = b_seq;
    let myCurpage = curPage;
    if(mySeq === undefined || mySeq === null || mySeq === ''){mySeq = boardseq;}
    if(myCurpage === undefined || myCurpage === null || myCurpage === ''){myCurpage = 1;}
    let url = '../comment/list.do?b_seq='+mySeq+'&curPage='+myCurpage;
    $.ajax({
        // ajax 속성들 정의
        type : "GET",
        // 동기식으로 설정
        async : false,
        url : url,
        success: function (result){
            let html = "";
            // 댓글목록 html
            if(result.length > 0){
                for(let i = 0; i < result.length - 1; i++){
                    html += "<li class='comment_box' id='comment-"+result[i].c_seq+"'>";
                    html += "<div class='profile_info'>";
                    html += "<span id=writer'>"+result[i].c_writer+"</span><span>" + result[i].c_regdate+"</span>";
                    if(sessionUserName == result[i].c_writer){
                        let c_seq = "'"+result[i].c_seq+"'";
                        let c_writer = "'"+result[i].c_writer+"'";
                        //console.log(c_seq+', '+c_writer);
                        html += "<button type='button' class='btn_st1 btn_radius5 comment_del' style='background-color: #ff7373;' onclick='commentDelete(this)'>삭제</button>";
                        html += "<button type='button' id='btn_modi' class='btn_st1 btn_radius5 comment_modi' " +
                                'onclick="showUpdateComment('+c_seq+','+c_writer+')">수정</button>';
                    }
                    html += "</div>";
                    html += "<div class='comment_text'>"
                    html += "<p>"+result[i].c_content+"</p>";
                    html += "</div>";
                    html += "</li>";
                }
                // 페이징 정보들
                const totalPage = result[result.length - 1].totalPage;
                const totalPost = result[result.length - 1].totalPost;
                const curPage = result[result.length - 1].curPage;
                const startPage = result[result.length - 1].startPage;
                const endPage = result[result.length - 1].endPage;
                // pagination html
                html += "<li>";
                html += "<div class='pagination'>";
                if(curPage - 1 >= 1){
                    html += '<a href="javascript:goPage('+b_seq+', '+(curPage-1)+');" class="nextpage pbtn"> ' +
                        "<img src=\"/myboard/resources/images/btn_prevpage.png\" alt=\"다음 페이지로 이동\"></a>";
                }
                if(startPage != 1){
                    html += '<a href="javascript:goPage('+b_seq+', 1);"><span class="pagenum">1</span></a>' +
                        "<div class=\"pageDot\"><span> ... </span></div>";
                }
                for(let i = startPage; i < curPage; i++){
                    html += '<a href="javascript:goPage('+b_seq+', '+i+');"><span class="pagenum">'+i+'</span></a>';
                }
                html += "<a href='javascript:;'><span class='pagenum currentpage'>"+curPage+"</span></a>";
                for(let i = curPage+1; i <= endPage; i++){
                    html += '<a href="javascript:goPage('+b_seq+', '+i+');"><span class="pagenum">'+i+'</span></a>';
                }
                if(endPage < totalPage){
                    html += "<div class=\"pageDot\"><span> ... </span></div>" +
                        '<a href="javascript:goPage('+b_seq+', '+totalPage+');"><span class="pagenum">'+totalPage+'</span></a>';
                }
                if(curPage + 1 <= totalPage){
                    html += '<a href="javascript:goPage('+b_seq+', '+(curPage+1)+');" class="nextpage pbtn">' +
                        " <img src=\"/myboard/resources/images/btn_nextpage.png\" alt=\"다음 페이지로 이동\"></a>";
                }
                html += "</div>";
                html += "</li>";
            }else{
                html += "<p>게시글이 없습니다.</p>";
            }
            $('.div_comment_list').html(html);
        },
        error:function(request,status,error){
            console.log('comment list error');
        }
    })
}
```

댓글 입력

```javascript
const commentSubmit = function(){
    // 댓글을 작성하지 않았다면 false
    if(!$('.form_comment #content').val()){
        alert('댓글을 작성해주세요.');
        return false;
    }
    $.ajax({
        // ajax 속성들 정의
        type : "POST",
        url : "../comment/write.do",
        data : $('.form_comment').serialize(),
        success : function (data){
            if(data == "success"){
                // 목록을 업데이트
                getCommentList();
                // 입력창을 초기화함
                $('#form_comment #content').val("");
            }else{
                console.log('comment write fail!');
            }
        },
        error:function(request,status,error){
            console.log('comment write error');
        }
    })
}
```

댓글 삭제

```javascript
const commentDelete = function(el){
    const bDel = confirm('정말로 삭제하시겠습니까?');
    if(bDel){
        // id 와 작성자정보를 가져옵니다.
        const commentBox = el.closest('.comment_box');
        const c_seq = commentBox.id.split('-')[1];
        const c_writer = document.getElementById('writer').value;
        // get방식으로
        $.get("../comment/delete.do?c_seq=" + c_seq + "&writer=" + c_writer,
            function (data) {
                if (data == "success") {
                    // 삭제가된 목록 업데이트
                    getCommentList();
                } else {
                    console.log('comment delete fail!!!');
                }
            }
        ).fail(function () {
                console.log('comment delete error');
            }
        )
    }
}
```

댓글 수정창 생성

```javascript
const showUpdateComment = function(c_seq){
    // 여러개 눌러도 댓글창 1개만 보이기 위해서 목록 초기화 
    getCommentList();
    // 댓글 입력창 생성
    let html = "";
    html += "<form class='form_comment' id='form_comment' action='../comment/update.do' method='post'>";
    html += "<fieldset>";
    html += "<div class='commnet_area'>";
    html += "<input type='hidden' name='seq' value="+c_seq+">"
    html += "<input type='hidden' name='writer' value="+sessionUserName+">";
    html += "<input type='hidden' name='boardseq' value="+boardseq+">";
    html += "<textarea id='content' name='content' placeholder='댓글을 적을수 있는 창입니다.'></textarea>";
    html += "<p class=\"btn_line txt_right\">";
    const commentId = "'#comment-"+c_seq+"'";
    html += "<button type='button' class='btn_st1 btn_radius5' style='background-color: #ff7373;' " +
        'onclick="commentUpdate('+commentId+')">등록</button>'
    html += "</p>";
    html += "</div>";
    html += "</fieldset>";
    html += "</form>";
    $('#comment-'+c_seq).append(html);

    // 정보 셋팅
    const text = $('#comment-'+c_seq+' .comment_text p').text();
    //console.log(text);
    $('#comment-'+c_seq+' #form_comment #content').val(text);
}
```

댓글 수정

```javascript
const commentUpdate = function(commentId){
    // 입력 텍스트 가져오기
    const content = $(commentId+' #form_comment #content').val();
    if(content == null || content == ""){
        alert('댓글을 작성해주세요.');
        return false;
    }

    $.ajax({
        type : "POST",
        url : "../comment/update.do",
        data : $(commentId+' #form_comment').serialize(),
        success : function (data){
            if (data == "success") {
                getCommentList();
            } else {
                console.log('comment update fail!!!');
            }
        },
        error : function (){
            console.log('comment update error');
        }
    })
}
```

