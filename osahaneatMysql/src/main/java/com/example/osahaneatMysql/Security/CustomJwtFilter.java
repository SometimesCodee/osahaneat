package com.example.osahaneatMysql.Security;

import com.example.osahaneatMysql.Utils.JwtUtilsHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    //filter chinh, check token co verify khong, neu co thi grant authentication cho user truy cap cac api
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenHeader(request);
        if (token != null) {
            //giai ma token
            if(jwtUtilsHelper.verifyToken(token)){
                //tao ra chung thuc (authentication)
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(usernamePasswordAuthenticationToken);

                System.out.println("verified token");
            }else{
                System.out.println("verified token failed");
            }
        }

        filterChain.doFilter(request, response);
    }

    //lay token tren header
    public String getTokenHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        return token;
    }
}
