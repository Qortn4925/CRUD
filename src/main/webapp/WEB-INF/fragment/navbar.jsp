<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div>
            <div style="display: flex; gap: 10px">
                     <div>
                         <a href="/board/list"> 글 목록</a>
                     </div>
                <div>
                    <a href="/board/new"> 글 작성</a>

                </div>
            </div>
    </div>

 <c:if test="${not empty message}">
     <style>
         .success{
             background-color: skyblue;
             margin: 10px;
         }
     </style>
        <div class="${message.type}">
        <h5>${message.text}</h5></div>
 </c:if>

</body>
</html>
