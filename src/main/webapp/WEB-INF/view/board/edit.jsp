<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>

<h2> ${board.id} 수정</h2>

<form action="" method="post">
   <div>제목
     <input type="text" value="${board.title}">
   </div>
  <div> 본문
    <textarea name="" id="" cols="30" rows="10"></textarea>
   ${board.content}</div>
  <div> 작성자
    <input type="text" value="${board.writer}">
  </div>

  <div>
  <button> 저장</button>
  </div>


</form>

</body>
</html>
