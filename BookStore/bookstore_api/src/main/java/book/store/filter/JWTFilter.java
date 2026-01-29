package book.store.filter;

import book.store.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// Có request thì chạy vào filter
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTHelperUtils jwtHelperUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        try {
            String token = header.substring(7);
            String data = jwtHelperUtils.validateToken(token);
            System.out.println(data);
            if (!data.isEmpty()) {
                // Tạo chứng thực cho spring security
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            System.out.println("Token không hợp lệ + : " + e);
        }
        // cho phép người dùng đi vào link cần phải xác thực. Không có hàm này cũng được.
        filterChain.doFilter(request, response);
    }
}
