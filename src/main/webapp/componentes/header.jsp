<%@page import="Logica.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<header class="header-container">

    <!-- Imagen de fondo -->
    <div class="header-image-container">
        <img src="${pageContext.request.contextPath}/img/header.jpg" alt="TechGuard" class="header-image">
    </div>


    <nav class="navbar">
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
            <li><a href="${pageContext.request.contextPath}/nosotros.jsp">Nosotros</a></li>
            <li><a href="${pageContext.request.contextPath}/contacto.jsp">Contactos</a></li>
            <li><a href="${pageContext.request.contextPath}/reporteAnonimo.jsp">Anonimo</a></li>
            <li><a href="${pageContext.request.contextPath}/ChatBot.jsp">ChatBot</a></li>
            <li><a href="${pageContext.request.contextPath}/reportar.jsp">Reporte</a></li>
        </ul>
        <div class="user-actions">
            <% if (usuario != null) { %>
            <a href="${pageContext.request.contextPath}/user.jsp" class="user-link">
                <img src="${pageContext.request.contextPath}/img/user.png" alt="Usuario" class="user-icon">
            </a>
            <a href="${pageContext.request.contextPath}/logout" class="user-link">
                <img src="${pageContext.request.contextPath}/img/logout.png" alt="Cerrar sesión" class="user-icon">
            </a>
            <% } else { %>
            <a href="${pageContext.request.contextPath}/login.jsp" class="btn-primary">Iniciar Sesión</a>
            <% } %>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="header-content">
        <h1>TechGuard: Voces valientes</h1>
        <p>
            El ciberbullying es una problemática que afecta a millones de niños y jóvenes en todo el mundo, causando
            profundas consecuencias emocionales, psicológicas y físicas.
        </p>
        <a href="${pageContext.request.contextPath}/nosotros.jsp" class="btn-recursos">Recursos</a>
    </div>
</header>
