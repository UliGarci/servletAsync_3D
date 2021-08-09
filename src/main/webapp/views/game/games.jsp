<%--
  Created by IntelliJ IDEA.
  User: ulii_
  Date: 04/08/2021
  Time: 11:58 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Videojuegos</title>
</head>
<body>
<div class="contenido">
    <h2>Videojuegos</h2>
</div>
<div>
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>ID Categoria</th>
            <th>Categoria</th>
            <th>Nombre del juego</th>
            <th>Fecha de registro</th>
            <th>Stauts</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listgames}" var="game" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${game.getIdcategory}</td>
                <td>${game.getNamecategory()}</td>
                <td>${game.getNombregame()}</td>
                <td>${game.getDatepremiere()}</td>
                <td>${game.getStatus()}</td>
                <td><img src="data:image/jpeg;base64,${ game.imgGame }" ></td>
                <td>
                    <button type="button" class="btn btn-primary">Modificar</button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete">Eliminar</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="JS/index.js"></script>
</body>
</html>