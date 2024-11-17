
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <jsp:include page="/componentes/head.jsp" /> 

    <body>
        <div class="contenedorPrincipal">
            <main> 
                <% if (request.getParameter("registroExitoso") != null) { %>
                <p class="mensaje-exito">Registro exitoso. Ahora puedes iniciar sesión.</p>
                <% } %>

                <!-- Sección de Encabezado -->

                <jsp:include page="/componentes/header.jsp" />


                <!-- Sección de Login -->
                <section class="login-container">
                    <div class="login-content">
                        <div class="login-form">
                            <form action="UsuarioServlet" method="post">
                                <h2>Iniciar Sesión</h2> 

                                <label for="email">Correo Electrónico</label>
                                <input type="email" id="email" name="email" placeholder="Correo Electrónico" required>

                                <label for="password">Contraseña</label>
                                <input type="password" id="password" name="password" placeholder="Contraseña" required>

                                <div class="login-options">
                                    <label class="remember-me">
                                        <input type="checkbox" name="remember"> Recordar
                                    </label>
                                    <a href="#" class="forgot-password">¿Olvidaste la contraseña?</a>
                                </div>

                                <button type="submit" class="login-button">Acceder</button>

                                <p class="register-link">¿No tienes una cuenta? <a href="register.jsp">Crear una cuenta</a></p>
                            </form>
                        </div>
                        <div class="login-image">
                            <img src="${pageContext.request.contextPath}/img/loginVet.jpg" alt="Imagen Login">
                        </div>
                    </div>
                </section>
            </main>
            <jsp:include page="/componentes/footer.jsp" />

        </div>
    </body>

</html>
