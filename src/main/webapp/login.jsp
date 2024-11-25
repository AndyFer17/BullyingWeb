<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <jsp:include page="/componentes/head.jsp" />

<head>
    <!-- Incluir auth.css para estilos específicos -->
    <link rel="stylesheet" href="css/auth.css">
</head>

<body>
    <div class="contenedorPrincipal">
        <main>
            <% if (request.getParameter("registroExitoso") != null) { %>
            <p class="mensaje-exito">Registro exitoso. Ahora puedes iniciar sesión.</p>
            <% } %>

            <jsp:include page="/componentes/header.jsp" />

            <!-- Contenedor del login con diseño moderno -->
            <div class="container" id="container">
                <!-- Formulario de Inicio de Sesión -->
                <div class="form-container sign-in">
                    <form action="UsuarioServlet" method="post">
                        <h1>Iniciar Sesión</h1>
                        <span>Accede con tu correo y contraseña</span>
                        <label for="email">Correo Electrónico</label>
                        <input type="email" id="email" name="email" placeholder="Correo Electrónico" required>

                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" placeholder="Contraseña" required>

                        <div class="login-options">
                            <label class="remember-me">
                                <input type="checkbox" name="remember"> Recordar
                            </label>
                            <a href="forgot-password.jsp" class="forgot-password">¿Olvidaste tu contraseña?</a>
                        </div>

                        <button type="submit" class="login-button">Acceder</button>
                    </form>
                </div>

                <!-- Mensaje para redirigir al registro -->
                <div class="toggle-container">
                    <div class="toggle">
                        <div class="toggle-panel toggle-left">
                            <h1>¡Bienvenido de nuevo!</h1>
                            <p>Ingrese sus datos personales para utilizar todas las funciones del sitio.</p>
                            <button class="hidden" id="login">Iniciar Sesión</button>
                        </div>
                        <div class="toggle-panel toggle-right">
                            <h1>¿Eres nuevo aquí?</h1>
                            <p>Regístrate con tus datos personales y accede a todas las funciones.</p>
                            <button class="hidden" id="register" onclick="location.href='register.jsp'">Regístrate</button>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/componentes/footer.jsp" />
    </div>

    <!-- Script específico para esta página -->
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            // Selección de elementos principales
            const container = document.getElementById("container");
            const registerBtn = document.getElementById("register");
            const loginBtn = document.getElementById("login");

            // Si los botones existen, se añaden los eventos
            if (registerBtn && loginBtn) {
                // Al hacer clic en el botón de registro
                registerBtn.addEventListener("click", () => {
                    container.classList.add("active");
                });

                // Al hacer clic en el botón de login
                loginBtn.addEventListener("click", () => {
                    container.classList.remove("active");
                });
            }
        });
    </script>
</body>

</html>
