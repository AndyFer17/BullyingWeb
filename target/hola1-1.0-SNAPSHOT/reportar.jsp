<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Usuario"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%
    HttpSession userSession = request.getSession(false); 
    Usuario usuario = (userSession != null) ? (Usuario) userSession.getAttribute("usuario") : null;

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="/componentes/head.jsp" />

    <body>
        <div class="contenedorPrincipal"> 
        <jsp:include page="/componentes/header.jsp" />
        
        <main> 
        <section class="mensajes">
            <% if (request.getParameter("error") != null) { %>
                <p class="mensaje-error">Error al agendar el reporte. Por favor, inténtalo de nuevo.</p>
            <% } %>
            <% if (request.getParameter("errorAfectado") != null) { %>
                <p class="mensaje-error">Error al agregar los datos del afectado. Verifica los campos.</p>
            <% } %>
            <% if (request.getParameter("reportado") != null) { %>
                <p class="mensaje-exito">Reporte agendado exitosamente.</p>
            <% } %>
        </section>

        <!-- Formulario de Agendar Reporte -->
        <section>
            <h2>Agendar Reporte</h2>
            <form action="AgendarReporteServlet" method="post">
                <div class="form-group">
                    <label for="nombreCliente">Nombre del Cliente:</label>
                    <input type="text" id="nombreCliente" name="nombreCliente" value="<%= usuario.getNombre() %>" readonly>
                </div>

                <div>
                    <label for="nombreAfectado">Nombre del Afectado:</label>
                    <input type="text" id="nombreAfectado" name="nombreAfectado" required>
                </div>

                <div>
                    <label for="agresion">Servicio:</label>
                    <select id="agresion" name="servicio" required>
                        <option value="verbal">Verbal</option>
                        <option value="fisico">Fisico</option>
                        <option value="acoso">Acoso</option>
                        <option value="CiberBullying">Ciber Bullying</option>
                    </select>
                </div>

                <div>
                    <label for="fechaHora">Fecha y Hora:</label>
                    <input type="datetime-local" id="fechaHora" name="fechaHora" required>
                </div>

                <div>
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea>
                </div>

                <button type="submit" class="btn-agendar">Agendar Reporte</button>
            </form>
        </section>
        </main>

        <jsp:include page="/componentes/footer.jsp" />
        </div>
    </body>
</html>
