package it.academy.servlets.filters;

import io.jsonwebtoken.*;
import it.academy.components.JwtProvider;
import it.academy.dto.response.LoginResponse;
import it.academy.service.JwtService;
import it.academy.service.impl.JwtServiceImpl;
import it.academy.utils.Constants;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@WebFilter("/api/students/*")
public class SecurityFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        JwtProvider provider = new JwtProvider();
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = httpRequest.getHeader(Constants.AUTHORIZATION_HEADER);
        if (token != null && token.startsWith(Constants.TOKEN_PATTERN)) {
            try {
                String jwtToken = token.substring(7);
                // Verify and decode JWT token
                if (!provider.validateAccessToken(jwtToken)) {
                    if (extractRefreshToken(httpRequest, response, provider)) {
                        chain.doFilter(req, res);
                        return;
                    }
                    log.error(Constants.TOKEN_ERROR);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.TOKEN_ERROR);
                    httpRequest.getSession().setAttribute(Constants.AUTHENTICATION_KEY, Constants.FALSE);
                    return;
                }
                Claims claims = provider.getAccessClaims(jwtToken);
                // Extract user details from token
                String email = claims.getSubject();
                System.out.println(email + " <--- HERE");

                String roles = claims.get(Constants.ROLES_KEY).toString();
                httpRequest.getSession().setAttribute(Constants.ROLES_KEY, roles);

                System.out.println(roles + " " + roles.contains("DEFAULT_USER"));

                httpRequest.getSession().setAttribute(Constants.AUTHENTICATION_KEY, Constants.TRUE);
                log.info(Constants.AUTHENTICATION_SUCCESSFUL);
                // If authorized, proceed with resource access
            } catch (JwtException e) {
                // Token verification failed
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        } else {
            // Token missing or invalid
            if (extractRefreshToken(httpRequest, response, provider)) {
                chain.doFilter(req, res);
                return;
            }
            System.out.println("Token missing");
            log.error(Constants.TOKEN_ERROR);
            httpRequest.getSession().setAttribute(Constants.AUTHENTICATION_KEY, Constants.FALSE);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.TOKEN_MISSING_ERROR);
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean extractRefreshToken(HttpServletRequest request, HttpServletResponse response, JwtProvider provider) {
        List<Cookie> cookies = Arrays.stream(request.getCookies())
                .filter(c -> Objects.equals(c.getName(), Constants.REFRESH_TOKEN_KEY))
                .collect(Collectors.toList());
        if (!cookies.isEmpty()) {
            String refresh = cookies.get(0).getValue();
            if (provider.validateRefreshToken(refresh)) {
                JwtService jwtService = new JwtServiceImpl();
                LoginResponse resp = jwtService.updateTokens(refresh);

                System.out.println(resp);

                log.info(Constants.AUTHENTICATION_SUCCESSFUL);
                response.setHeader(Constants.UPDATED_HEADER, resp.getAccessToken());
                request.getSession().setAttribute(Constants.REFRESH_TOKEN_KEY, resp.getRefreshToken());
                request.getSession().setAttribute(Constants.AUTHENTICATION_KEY, Constants.TRUE);
                return true;
            }
        }
        log.error(Constants.COOKIES_ERROR);
        return false;
    }
}
