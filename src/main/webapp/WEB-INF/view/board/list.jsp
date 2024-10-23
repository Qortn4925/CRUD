<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .active{
            background-color: yellow;
        }
    </style>
</head>
<body>

<h2> 게시물 목록</h2>

<table>

    <c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성 일시</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${boardList}" var="board">
        <tr>
            <td> ${board.id}</td>
            <td>
                <a href="/board/view?id=${board.id}">${board.title} </a>
            </td>
            <td> ${board.writer}</td>
            <td> ${board.inserted}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
<%--    dlwjs--%>
    <a href="/board/list?page=${pageInfo.previoudPageNumber}">이전 </a>

    <%--     pagination --%>
    <c:forEach begin="${pageInfo.leftPageNumber}"
               end="${pageInfo.rightPageNumber}"
               var="pageNumber">
        <a class =${pageInfo.currentPageNumber == pageNumber?'active':''} href="/board/list?page=${pageNumber}">
                ${pageNumber}
        </a>
    </c:forEach>

    <a href="/board/list?page=${pageInfo.nextPageNumber}">다음 </a>



</div>

</body>
</html>
