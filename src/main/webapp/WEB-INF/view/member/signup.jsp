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
</head>
<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col col-mg-8 col-lg-6">
            <h2 class="my-3">
                회원 가입
            </h2>

            <form method="post">
<%--                id, password, nickName , description --%>

    <div class="mb-3">
        <label for="inputId" class="form-label">아이디</label>
        <input type="text" name="id" id="inputId" class="form-control">
    </div>
    <div class="mb-3">
        <label for="inputPassword" class="form-label">비밀번호</label>
        <input type="text"  name="password"id="inputPassword" class="form-control">
    </div>
    <div class="mb-3">
        <label for="inputNickname" class="form-label"> 별칭</label>
        <input type="text" name="nickName" id="inputNickname" class="form-control">
    </div>
    <div class="mb-3">
        <label for="textDescription1"  nam class="form-label"> 소개</label>
        <textarea type="text"  rows="10" name="description"
                  id="textDescription1"
                  class="form-control">

        </textarea>
    </div>
    <div class="mb-3">
        <button class="btn btn-primary">
            가입
            <i class="fa-solid fa-user-plus"></i>
        </button>
    </div>

            </form>
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
