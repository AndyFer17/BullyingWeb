package Servlets;

import Logica.Anonimo;
import Persistencia.ReporteAnonimoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/AnonimoServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 50   // 50 MB
)
public class AnonimoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads"; // Carpeta relativa para imágenes

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Capturar datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String nombreAcusado = request.getParameter("nombreAcusado");
        String comentario = request.getParameter("comentario");

        // Procesar la imagen
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Crear el directorio si no existe
        }

        String fileName = null;
        for (Part part : request.getParts()) {
            if (part.getName().equals("imagen")) {
                fileName = extractFileName(part).replaceAll("[^a-zA-Z0-9\\.\\-_]", "_"); // Limpia el nombre del archivo
                if (fileName != null && !fileName.isEmpty()) {
                    part.write(uploadPath + File.separator + fileName); // Guarda la imagen físicamente
                }
            }
        }

        String relativePath = UPLOAD_DIR + "/" + fileName; // Genera ruta relativa

        // Crear objeto Anonimo
        Anonimo anonimo = new Anonimo(nombre, apellido, email, nombreAcusado, comentario, relativePath);

        // Guardar en la base de datos
        ReporteAnonimoDAO dao = new ReporteAnonimoDAO();
        boolean guardado = dao.guardarAnonimo(anonimo);

        if (guardado) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
