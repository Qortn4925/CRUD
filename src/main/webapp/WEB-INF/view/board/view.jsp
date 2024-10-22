<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>

  <h2> 게시글 보기</h2>
  <div>
    제목
    <input type="text" value="${board.title}">
  </div>
  <div>
    본문
    <textarea name="" id="" cols="30" rows="10" >${board.content}</textarea>
  </div>
  <div>
    작성자
    <input type="text" value="${board.writer}" readonly>
  </div>
  <div>
        작성일시
    <input type="text" value="${board.inserted}">
  </div>
</body>
</html>
