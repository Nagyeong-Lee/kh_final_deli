<%--
  Created by IntelliJ IDEA.
  User: 이나경
  Date: 2023-03-04
  Time: 오후 2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>딜리 - 게시판 메인 페이지</title>
    <%@ include file="/WEB-INF/views/global/pc-commonLib.jsp" %>
    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
    </script>

    <style>
        *{
            box-sizing border-box;
            text-align:center;
        }

        #myTable *{
             cursor: pointer;
        }

        #myTable {
            margin :auto;
            margin-top : 30px;
            width: 500px;
            cursor: pointer;
            border:1px solid black;
        }

        .container {
            text-align: center;
            margin-top : 50px;
        }

        /*제목 길이 넘치는거 자름*/
        .title {
            overflow: hidden;
            white-space: nowrap;
            word-break: break-word;
            text-overflow: ellipsis;
            max-width: 270px;
        }

    </style>
</head>

<body>
<%@include file="/WEB-INF/views/customHeader/owner_nav.jsp" %>
<div class="container">

    <%--검색--%>
    <div class="search">
        <form action="/owner/community/search?currentPage=1">
            <select name="search_type">
                 <option value="writer">작성자</option>
                 <option value="title">제목</option>
                 <option value="contents">내용</option>
            </select>
            <input type="text" class="keyword" id="keyword" name="keyword">
            <input type="submit" value="검색">
        </form>
    </div>

    <%--글 목록 보여주기--%>
    <table id="myTable" class="display">
        <thead>
            <tr style="border:1px solid black;">
                <th>작성자</th>
                <th>게시글 제목</th>
                <th>작성일자</th>
                <th>수정일자</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty boardSearchList}">
                    <c:forEach var="i" items="${boardSearchList}">
                        <tr style="border:1px solid black;">
                            <td class="writer" id="b_writer">${i.b_writer}</td>
                            <td class="title" id="b_title"><a href="/owner/community/detailBoard?b_seq=${i.b_seq}">${i.b_title}</a></td>
                            <td class="write_time" id="b_write_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                                     value="${i.b_write_time}"/></td>
                            <td class="upd_time" id="b_upd_time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                                 value="${i.b_upd_time}"/></td>
                            <td class="count" id="b_count">${i.b_count}</td>

                            <input type="hidden" id="b_seq" value="${i.b_seq}">
                            <input type="hidden" id="b_content" value="${i.b_contents}">
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                     <td colspan="5">게시글이 존재하지 않습니다.</td>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <br>

    <%--페이징처리--%>
    <div class="paging">
           ${paging}
    </div>
    <br>
    <a href="/owner/community/writeForm">
        <input type="button" id="write_btn" value="글작성"></input>
    </a>
</div>

</body>
</html>
