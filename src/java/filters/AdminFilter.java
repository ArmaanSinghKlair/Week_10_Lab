/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;
import services.AccountService;

/**
 *
 * @author 839645
 */
public class AdminFilter implements Filter {
    
   
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
                
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            HttpSession sess = httpRequest.getSession();
            String email = (String) sess.getAttribute("email");

            AccountService as = new AccountService();
            User user = as.getUser(email);
            
            if (user.getRole().getRoleId() != 1) {
                httpResponse.sendRedirect("notes");
                return;
            }
            chain.doFilter(request, response);
      
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


   
}
