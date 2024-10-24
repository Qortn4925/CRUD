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
  <c:import url="/WEB-INF/fragment/navbar.jsp">
  <c:param name="active" value="new"/>
  </c:import>

  <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">

          <h2> 게시글 보기</h2>
          <div class="mb-3">
            <label for="inputTitle" class="form-label">      제목</label>

              <input class="form-control"  id="inputTitle" type="text" value="${board.title}">
          </div>
          <div class="mb-3">
            <label for="inputContents" class="form-label"> 본문</label>
              <textarea class="form-control" name=""  id="inputContents"  rows="10" >${board.content}</textarea>
          </div>
          <div class="mb-3">
            <label for="inputWriter" class="form-label"></label>
              작성자
              <input class="form-control" id="inputWriter" type="text" value="${board.writer}" readonly>
          </div>
          <div class="mb-3">
            <label for="inputDate" class="form-label">  작성일시</label>

              <input class="form-control" id="inputDate" type="text" value="${board.inserted}">
          </div>

          <div>
              <button type="button" form="deleteForm1"  class="btn btn-outline-danger"
                      data-bs-toggle="modal" data-bs-target="#deleteControlModal">
                삭제</button>
            <a class="btn btn-primary" href="/board/edit?id=${board.id}"> 수정</a>
          </div>

          <form id ="deleteForm1" action="/board/delete" method="post">
            <input type="hidden" name="id" value="${board.id}">
          </form>

      </div>
    </div>
  </div>

  <div class="modal fade" id="deleteControlModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          ${board.id}번 게시물을 삭제하시겠습니까?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          <button form="deleteForm1"  class="btn btn-danger">삭제</button>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
          integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
          crossorigin="anonymous"></script>
</body>
</html>
