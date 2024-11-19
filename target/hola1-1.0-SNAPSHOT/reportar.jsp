<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Usuario"%>
<%@page import="Persistencia.ClienteDAO" %>
<%@page import="Persistencia.AfectadoDAO" %>
<%@page import="Persistencia.ReporteDAO" %>
<%@page import="Logica.Afectado" %>
<%@page import="Logica.Reporte" %>
<%@page import="java.time.LocalDate" %>
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
                    <p>Error al agendar el reporte. Por favor, inténtalo de nuevo.</p>
                    <% } %>
                    <% if (request.getParameter("errorAfectado") != null) { %>
                    <p>Error al agregar los dato dsel afectado. Verifica los campos.</p>
                    <% } %>
                    <% if (request.getParameter("errorClienteNoEncontrado") != null) { %>
                    <p>No se encontró un cliente asociado a tu cuenta. Por favor, regístrate o contacta con el soporte.</p>
                    <% } %>
                    <% if (request.getParameter("reportado") != null) { %>
                    <p>Reporte agendado exitosamente.</p>
                    <% } %>
                </section>

                <!-- Formulario de Agendar Reporte -->
                <section>
                    <h2>Agendar Reporte</h2>
                    <form action="AgendarReporteServlet" method="post">
                        <div>
                            <label for="nombreCliente">Nombre del Cliente:</label>
                            <input type="text" id="nombreCliente" name="nombreCliente" value="<%= usuario.getNombre() %>" readonly>
                        </div>

                        
                            <label for="nombreAfectado">Nombre del Afectado:</label>
                            <input type="text" id="nombreAfectado" name="nombreAfectado" required>
                            <label for="servicio">Agresion</label>
                            <select id="servicio" name="servicio" required>
                                <option value="verbal">Verbal</option>
                                <option value="fisico">Fisico</option>
                                <option value="acoso">Acoso</option>
                                <option value="CiberBullying">Ciber Bullying</option>
                            </select>
                      

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
