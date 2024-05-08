package es.unex.cum.tw.filters;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carta/*")
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceImpl();
        Optional<User> userOptional = service.authenticate((HttpServletRequest) request);
        if (userOptional.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta pagina!");
        }
    }
}
