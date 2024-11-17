<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<jsp:include page="/componentes/head.jsp" />

<body>



    <!-- Sección de Registro -->
    <section class="register-container">
        
        <div class="register-content">
            <div class="register-image">
                <img src="${pageContext.request.contextPath}/img/loginVet.jpg" alt="Imagen Registro">
            </div>
            <div class="register-form">
                <h2>Crear una Cuenta</h2>
                <form action="RegistroServlet" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>
                    </div>

                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Correo Electrónico</label>
                        <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required>
                    </div>

                    <button type="submit" class="register-button">Registrar Cuenta</button>
                </form>

                <p class="login-link">¿Ya tienes una cuenta? <a href="login.jsp">Inicia Sesión aquí</a></p>
            </div>
        </div>
    </section>

    <jsp:include page="/componentes/footer.jsp" />
</body>

</html>
