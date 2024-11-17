<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
 <jsp:include page="/componentes/head.jsp" />
<body>
           <div class="contenedorPrincipal"> 
        <jsp:include page="/componentes/header.jsp" />
        <main> 
            
    <h1>Enviar Reporte Anónimo</h1>
    <form action="AnonimoServlet" method="post" enctype="multipart/form-data">
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required><br>

        <label for="apellido">Apellido:</label>
        <input type="text" name="apellido" id="apellido" required><br>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required><br>

        <label for="nombreAcusado">Nombre del acusado:</label>
        <input type="text" name="nombreAcusado" id="nombreAcusado"><br>

        <label for="comentario">Comentario:</label>
        <textarea name="comentario" id="comentario" required></textarea><br>

        
        <button type="submit">Enviar</button>
    </form>
    
    
              </main>
          
         
         

        <jsp:include page="/componentes/footer.jsp" />
        
    </div>
</body>
</html>
