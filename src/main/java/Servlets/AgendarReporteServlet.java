package Servlets;
import Logica.Reporte;
import Logica.Afectado;
import Logica.Usuario;
import Persistencia.ReporteDAO;
import Persistencia.ClienteDAO;
import Persistencia.AfectadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/AgendarReporteServlet")
public class AgendarReporteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int idUsuario = usuario.getIdUsuario();
        
        ClienteDAO clienteDAO = new ClienteDAO();
        int idCliente = clienteDAO.obtenerIdClientePorUsuarioId(idUsuario);

        if (idCliente == -1) {
            response.sendRedirect("reportar.jsp?errorClienteNoEncontrado=true");
            return;
        }

        // Capturar los parámetros del formulario
        String nombreAfectado = request.getParameter("nombreAfectado");
        String agresion = request.getParameter("agresion");
        String servicio = request.getParameter("servicio");  // Captura el parámetro servicio
        String fechaHoraStr = request.getParameter("fechaHora");
        String descripcion = request.getParameter("descripcion");
        String estado = "pendiente";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

        Afectado afectado = new Afectado(idCliente, nombreAfectado, agresion, LocalDate.now());

        AfectadoDAO afectadoDAO = new AfectadoDAO();
        int idAfectado = afectadoDAO.agregarAfectado(afectado);

        if (idAfectado > 0) {
            Reporte reporte = new Reporte(idCliente, idAfectado, fechaHora, descripcion, estado, servicio);

            ReporteDAO reporteDAO = new ReporteDAO();
            boolean agendada = reporteDAO.generarReporte(reporte);
            
            if (agendada) {
                response.sendRedirect("reporte.jsp?reportado=true");
            } else {
                response.sendRedirect("reporte.jsp?error=true");
            }
        } else {
            response.sendRedirect("reporte.jsp?errorAfectado=true");
        }
    }
}
