package Shoppera.config;

import Shoppera.entity.User;
import Shoppera.repository.UserRepository;
import Shoppera.utils.AuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public AuthFilter(UserRepository userRepository, AuthUtil authUtil, HandlerExceptionResolver handlerExceptionResolver) {
        this.userRepository = userRepository;
        this.authUtil = authUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            final String requestHeader = request.getHeader("Authorization");
            if (requestHeader == null || !requestHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestHeader.split("Bearer ")[1];
            String username = authUtil.getUserNameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByUsername(username).orElseThrow();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            }
        }catch (Exception ex){
            handlerExceptionResolver.resolveException(request , response, null, ex);
        }
    }
}
