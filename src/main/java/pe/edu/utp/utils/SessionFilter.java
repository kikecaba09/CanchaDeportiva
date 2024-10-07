package pe.edu.utp.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización, si es necesaria
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // Obtener la sesión existente, no crear una nueva
            HttpSession session = httpRequest.getSession(false);

            // Aquí podrías agregar lógica para comprobar si la sesión es válida
            // Por ejemplo, redirigir si la sesión es nula para proteger ciertas páginas

            chain.doFilter(request, response); // Continuar con la cadena de filtros
        } else {
            chain.doFilter(request, response); // Continuar si no es un HttpServletRequest
        }
    }

    public void destroy() {
        // Limpieza, si es necesaria
    }
}
