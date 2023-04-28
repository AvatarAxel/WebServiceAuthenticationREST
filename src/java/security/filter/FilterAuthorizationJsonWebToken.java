/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import model.pojos.ResponseService;
import security.AuthorizationTokenJsonWebToken;

/**
 *
 * @author michi
 */
public class FilterAuthorizationJsonWebToken implements javax.servlet.Filter {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String PREFIX = "Bearer "; 

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        boolean validauth = false;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authheader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            if (authheader != null && authheader.startsWith(PREFIX)) {
                String token = authheader.replaceFirst(PREFIX, "");
                ResponseService r = AuthorizationTokenJsonWebToken.validateToken(token);
                if (r != null && !r.isError()) {
                    validauth = true;
                    chain.doFilter(request, response);
                }
            }

            if (!validauth) {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
                    httpServletResponse.getWriter().write("<h1>401 NO AUTORIZADO</h1>");
                }
            }
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
