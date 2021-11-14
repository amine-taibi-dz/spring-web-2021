package dz.acs.formation.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
/**
 * 
 * @author ataibi
 *
 */
public class AuthenticationSuccessWithSessionHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    public static final String USERNAME = "username";
   // public static final String PASSWORD = "password";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        request.getSession().removeAttribute(USERNAME);
        //request.getSession().removeAttribute(PASSWORD);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
       // request.getSession().setAttribute(PASSWORD, request.getParameter(PASSWORD));
        request.getSession().setAttribute(USERNAME, request.getParameter(USERNAME));
    }
}