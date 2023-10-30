package report.pflb.ProjectReport.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import report.pflb.ProjectReport.Entity.employees;
import report.pflb.ProjectReport.Entity.guests;
import report.pflb.ProjectReport.service.employeesService;
import report.pflb.ProjectReport.service.guestsService;


import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuth extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final employeesService employeesService;
    private final guestsService guestsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    )throws ServletException, IOException {
        System.out.println(request);
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;
        if (authHeader == null || !authHeader.startsWith("Bearer")){
                filterChain.doFilter(request,response);
                return;
            }
        jwt = authHeader.substring(7);
        userLogin = jwtService.extractLogin(jwt);
        if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = getUserDetails(userLogin);
            if (jwtService.tokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    private UserDetails getUserDetails(String login){
        employees employees = employeesService.findByE_mail(login);
        guests guests = guestsService.findByE_mail(login);
        if (employees != null) {
            return new org.springframework.security.core.userdetails.User(
                    employees.getEMail(),
                    employees.getPassword(),
                    employees.getAuthorities()
            );

        } else if (guests!=null) {
            return new org.springframework.security.core.userdetails.User(
                    guests.getEMail(),
                    guests.getPassword(),
                    guests.getAuthorities()
            );
        } else {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
    }
}
