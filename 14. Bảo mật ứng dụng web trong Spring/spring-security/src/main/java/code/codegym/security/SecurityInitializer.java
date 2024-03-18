package code.codegym.security;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
