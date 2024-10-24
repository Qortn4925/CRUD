<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<h2> 게시물 목록</h2>



<div class="table">
    <c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>

    <div>
        <div>

<table class="table">

    <thead>
    <tr>
        <th>번호</th>
        <th class="w-50">제목</th>
        <th><i class="fa-solid fa-user"></i></th>
        <th class="d-none d-lg-table-cell"><i class="fa-solid fa-calendar-days"></i></th>
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
            <td class="d-none d-lg-table-cell"> ${board.inserted}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

        </div>
    </div>
</div>



<div>
<%--    dlwjs--%>
    <c:if test="${pageInfo.hasPrevPage}">
    <a href="/board/list?page=${pageInfo.previoudPageNumber}">이전 </a>
    </c:if>
    <%--     pagination --%>
    <c:forEach begin="${pageInfo.leftPageNumber}"
               end="${pageInfo.rightPageNumber}"
               var="pageNumber">
        <a class =${pageInfo.currentPageNumber == pageNumber?'active':''} href="/board/list?page=${pageNumber}">
                ${pageNumber}
        </a>
    </c:forEach>

    <c:if test="${pageInfo.hasNextPage}">
    <a href="/board/list?page=${pageInfo.nextPageNumber}">다음 </a>
    </c:if>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
</body>
</html>
