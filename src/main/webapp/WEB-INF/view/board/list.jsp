<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        li.page-item {
            width: 50px;
            height: 50px;
        }

        a.page-link {
            width: 50px;
            height: 50px;
        }
    </style>
</head>
<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"></c:import>
<h2> 게시물 목록</h2>


<div class="table">


    <div>
        <div>

            <table class="table">

                <thead>
                <tr>
                    <th>번호</th>
                    <th class="w-50">제목</th>
                    <th>
                        <i class="fa-solid fa-user"></i>
                    </th>
                    <th class="d-none d-lg-table-cell">
                        <i class="fa-solid fa-calendar-days"></i>
                    </th>
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

<%-- 검색 form --%>
<%-- TODO : css 다듬기 --%>
<%--div.container>div.row>div.col-2+div.col-4+div.col-1--%>
<div class="container">
    <form class="row justify-content-center">
        <div class="col-2 col-lg-1">
            <select name="searchTarget" id="select1" class="form-select">
                <option value="all">전체</option>
                <option value="title" ${param.searchTarget=='title'?'selected':''}>제목</option>
                <option value="content"${param.searchTarget=='content'?'selected':''}>본문</option>
                <option value="writer"${param.searchTarget=='writer'?'selected':''}>작성자</option>
            </select>
        </div>
        <div class="col-4 col-lg-2">
            <input type="text" class="form-control" name="keyword" value="${param.keyword}">
        </div>
        <div class="col-1">
            <button class="btn btn-outline-primary h-100">
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </div>
    </form>
</div>


<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <%--    dlwjs--%>
        <c:if test="${pageInfo.hasPrevPage}">
            <li class="page-item">
                <a class="page-link" href="/board/list?page=${pageInfo.previousPageNumber}">
                    <i class="fa-solid fa-arrow-left"></i>
                </a>
            </li>
        </c:if>
        <%--     pagination --%>
        <c:forEach begin="${pageInfo.leftPageNumber}"
                   end="${pageInfo.rightPageNumber}"
                   var="pageNumber">
            <c:url value="" var="pagetLink">
                <c:param name="page" value="${pageNumber}"></c:param>
                <c:param name="searchTarget" value="${param.searchTarget}"></c:param>
                <c:param name="keyword" value="${param.keyword}"></c:param>
            </c:url>
            <li class="page-item">
                <a class="${pageInfo.currentPageNumber == pageNumber?'active':''}
            page-link" href="${pagetLink}">
                        ${pageNumber}
                </a>
            </li>
        </c:forEach>

        <c:if test="${pageInfo.hasNextPage}">
            <li class="page-item">
                <a class="page-link" href="/board/list?page=${pageInfo.nextPageNumber}">
                    <i class="fa-solid fa-arrow-right"></i>
                </a>
            </li>
        </c:if>
    </ul>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
</body>
</html>
