<%-- 
    Document   : form
    Created on : 13/11/2024, 07:01:19 PM
    Author     : BRASHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro de Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div style="margin-left: 100px; padding: 20px;">
            <form class="mt-3" action="${pageContext.request.contextPath}/usuario" method="post">
                <div class="row mb-4 d-flex justify-content-center">
                    <label for="inputNombre" class="col-sm-2 col-form-label">Nombre(s):</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control border-danger" id="inputNombre" maxlength="100" required name="nombre">
                    </div>
                </div>

                <div class="row mb-4 d-flex justify-content-center">
                    <label for="inputCurp" class="col-sm-2 col-form-label">CURP:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control border-danger" id="inputCurp" maxlength="18" required name="curp">
                    </div>
                </div>

                <div class="row mb-4 d-flex justify-content-center">
                    <label for="inputDescripcion" class="col-sm-2 col-form-label">Descripción:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control border-danger" id="inputDescripcion" maxlength="200" required name="descripcion">
                    </div>
                </div>

                <div class="row mb-4 d-flex justify-content-center">
                    <label for="inputApodo" class="col-sm-2 col-form-label">Apodo</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control border-danger" id="inputApodo" maxlength="100" required name="apodo">
                    </div>
                </div>
                
                <div class="row mb-4 d-flex justify-content-center">
                    <label for="inputEdad" class="col-sm-2 col-form-label">Edad:</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control border-danger" id="inputEdad" min="0" max="90" required name="edad">
                    </div>
                </div>

                <div class="col-auto d-flex justify-content-center">
                    <button type="submit" class="btn btn-danger">Agregar</button>
                </div>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
