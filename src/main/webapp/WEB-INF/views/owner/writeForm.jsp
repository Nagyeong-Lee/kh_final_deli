<%--
  Created by IntelliJ IDEA.
  User: 이나경
  Date: 2023-03-04
  Time: 오전 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>딜리 - 글작성하기</title>
    <%@ include file="/WEB-INF/views/global/pc-commonLib.jsp" %>
    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
    </script>

    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

    <script>
        $(function () {
            $(".summernote").summernote({
                height: 300,                 // 에디터 높이
                minHeight: null,             // 최소 높이
                maxHeight: null,             // 최대 높이
                focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
                lang: "ko-KR",					// 한글 설정
                placeholder: '글을 입력하세요.'	//placeholder 설정
            });
        });


    </script>

    <style>
        .container {
            margin: auto;
        }
    </style>
</head>

<body>
<%@include file="/WEB-INF/views/customHeader/owner_nav.jsp" %>
<form action="/owner/community/insert">
    <div class="container">
        <div>제목 :
            <input type="text" id="b_title" name="b_title"></div>
        <div>작성자 : ${loginEmail}</div>
        <input type="hidden" value="${loginEmail}" id="b_writer" name="b_writer">
        <textarea class="summernote" id="b_contents" name="b_contents"></textarea>
        <button id="submit" name="submit">작성하기</button>
        <a href="/owner/community">
            <button type="button">목록으로</button>
        </a>
    </div>
</form>

</body>
</html>
