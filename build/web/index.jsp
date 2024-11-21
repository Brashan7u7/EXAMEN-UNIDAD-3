<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .main-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }
        .main-container h1 {
            color: #343a40;
            margin-bottom: 30px;
            font-weight: bold;
        }
        .btn-custom {
            margin: 10px;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            text-decoration: none;
        }
        .btn-custom img {
            width: 20px;
            height: 20px;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h1 class="h1">Bienvenido al registro de usuarios</h1>
        <a href="${pageContext.request.contextPath}/views/form.jsp" class="btn btn-primary btn-lg btn-custom">
            <img src="https://img.icons8.com/ios-glyphs/30/FFFFFF/form.png" alt="Icono Formulario">
            Ingresar al formulario
        </a>
        <a href="${pageContext.request.contextPath}/usuario" class="btn btn-primary btn-lg btn-custom">
            <img src="https://img.icons8.com/ios-glyphs/30/FFFFFF/view-file.png" alt="Icono Vista">
            Ver formulario
        </a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>