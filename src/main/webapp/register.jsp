<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<jsp:include page="/componentes/head.jsp" />

<head>
    <!-- Incluir auth.css para los estilos específicos -->
    <link rel="stylesheet" href="css/auth.css">
</head>

<body>
    <div class="contenedorPrincipal">
        <jsp:include page="/componentes/header.jsp" />

        <!-- Contenedor principal del registro con diseño moderno -->
        <div class="container" id="container">
            <!-- Formulario de Registro -->
            <div class="form-container sign-up">
                <form action="RegistroServlet" method="post">
                    <h1>Crear una Cuenta</h1>
                    <span>Regístrate para acceder a todas las funciones</span>
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>

                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>

                    <label for="correo">Correo Electrónico</label>
                    <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>

                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Contraseña" required>

                    <button type="submit" class="register-button">Registrar Cuenta</button>
                </form>
            </div>

            <!-- Mensaje para redirigir al login -->
            <div class="toggle-container">
                <div class="toggle">
                    <div class="toggle-panel toggle-left">
                        <h1>¡Bienvenido de nuevo!</h1>
                        <p>Si ya tienes una cuenta, inicia sesión aquí.</p>
                        <button class="hidden" id="login" onclick="location.href='login.jsp'">Iniciar Sesión</button>
                    </div>
                    <div class="toggle-panel toggle-right">
                        <h1>¿Eres nuevo aquí?</h1>
                        <p>Regístrate con tus datos personales y accede a todas las funciones.</p>
                        <button class="hidden" id="register">Regístrate</button>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/componentes/footer.jsp" />
    </div>
</body>

</html>

