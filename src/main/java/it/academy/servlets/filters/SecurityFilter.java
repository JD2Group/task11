package it.academy.servlets.filters;

import io.jsonwebtoken.*;
import it.academy.components.JwtProvider;
import it.academy.dto.response.LoginResponse;
import it.academy.models.Role;
import it.academy.service.AuthService;
import it.academy.service.JwtService;
import it.academy.service.impl.AuthServiceImpl;
import it.academy.service.impl.JwtServiceImpl;
import it.academy.utils.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebFilter("/api/students/*")
public class SecurityFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        JwtProvider provider = new JwtProvider();
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                String jwtToken = token.substring(7);
                // Verify and decode JWT token
                if(!provider.validateAccessToken(jwtToken)){
                    if(extractRefreshToken(httpRequest, response, provider)){
                        chain.doFilter(req, res);
                        return;
                    }
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired!");
                    httpRequest.getSession().setAttribute("isAuthenticated", "false");
                    return;
                }
                Claims claims = provider.getAccessClaims(jwtToken);
                // Extract user details from token
                String email = claims.getSubject();
                System.out.println(email + " <--- HERE");

                String roles = claims.get("roles").toString();
                httpRequest.getSession().setAttribute("roles", roles);
                System.out.println(roles + " " + roles.contains("DEFAULT_USER"));

                httpRequest.getSession().setAttribute("isAuthenticated", "true");

                // If authorized, proceed with resource access
            } catch (JwtException e) {
                // Token verification failed
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        } else {
            // Token missing or invalid
            if(extractRefreshToken(httpRequest, response, provider)){
                chain.doFilter(req, res);
                return;
            }
            System.out.println("Token missing");
            httpRequest.getSession().setAttribute("isAuthenticated", "false");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token missing.");
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean extractRefreshToken(HttpServletRequest request, HttpServletResponse response, JwtProvider provider){
        List<Cookie> cookies = Arrays.stream(request.getCookies())
                .filter(c-> Objects.equals(c.getName(), "refresh_token"))
                .collect(Collectors.toList());
        if (!cookies.isEmpty()){
            String refresh = cookies.get(0).getValue();
            if (provider.validateRefreshToken(refresh)) {
                JwtService jwtService = new JwtServiceImpl();
                LoginResponse resp = jwtService.updateTokens(refresh);
                System.out.println(resp);
                response.setHeader("updated-access", resp.getAccessToken());
                request.getSession().setAttribute("refresh_token", resp.getRefreshToken());
                request.getSession().setAttribute("isAuthenticated", "true");
                return true;
            }
        }
        return false;
    }
}
