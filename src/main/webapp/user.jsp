<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Usuario, Logica.Afectado, Logica.Reporte" %>
<%@page import="Persistencia.AfectadoDAO, Persistencia.ReporteDAO, Persistencia.ClienteDAO" %>
<%@page import="java.util.List, jakarta.servlet.http.HttpSession, java.time.format.DateTimeFormatter" %>

<%
    HttpSession userSession = request.getSession(false); 
    Usuario usuario = (userSession != null) ? (Usuario) userSession.getAttribute("usuario") : null;

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return; 
    }

    ClienteDAO clienteDAO = new ClienteDAO();
    int idCliente = clienteDAO.obtenerIdClientePorUsuarioId(usuario.getIdUsuario());

    AfectadoDAO afectadoDAO = new AfectadoDAO();
    List<Afectado> afectados = afectadoDAO.obtenerAfectadoPorCliente(idCliente);

    ReporteDAO reporteDAO = new ReporteDAO();
    List<Reporte> reportes = reporteDAO.obtenerReportePorCliente(idCliente);
%>

<!DOCTYPE html>
<html lang="es">
    <jsp:include page="/componentes/head.jsp" />

<body>
    <div class="contenedorPrincipal"> 
        <jsp:include page="/componentes/header.jsp" />
        <main>
            
        
    <h2>Hola, <%= usuario.getNombre() %></h2>

    <h3>Tus reportes</h3>
    <% if (afectados != null && !afectados.isEmpty()) { %>
        <% for (Afectado afectado : afectados) { %>
            <p>Nombre: <%= afectado.getNombre() %></p>
        <% } %>
    <% } else { %>
        <p>No tienes reportes registrados.</p>
    <% } %>

    <h3>Tus citas</h3>
    <% if (reportes != null && !reportes.isEmpty()) { %>
        <% for (Reporte reporte : reportes) { %>
            <p>Descripción: <%= reporte.getDescripcion() %></p>
        <% } %>
    <% } else { %>
        <p>No tienes citas registradas.</p>
    <% } %>
    </main>
    
    <jsp:include page="/componentes/footer.jsp" />
        
    </div>
</body>
</html>
