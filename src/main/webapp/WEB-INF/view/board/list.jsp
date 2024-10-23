<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
            <td> <a href="/board/view?id=${board.id}">${board.title} </a></td>
            <td> ${board.writer}</td>
            <td> ${board.inserted}</td>
          </tr>
      </c:forEach>
      </tbody>
</table>

<div>
<%--     pagination --%>
    <:c:forEach begin="1" end="${lastPageNumber}" var="pageNumber">

    <a href="/boar/list?page=${pageNumber}"> </a> ${pageNumber}

    </:c:forEach>

</div>

</body>
</html>
