package book.store.provider;


import book.store.entity.Users;
import book.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenManagerProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Lấy email người dùng truyền lên
        String email = authentication.getName();
        // Lấy pass người dùng truyền lên
        String password = authentication.getCredentials().toString();
        // Layas thong tin người dùng truyền vào truy vẫn database.
        Users users = userRepository.findByEmail(email);
        if (users != null) {
            // So sánh password người dùng truyền vào với password Bcrypt lưu trong database
            // Mathch so sánh pass chưa mã hóa với pass đã mã hóa.
            if (passwordEncoder.matches(password, users.getPassword())) {
                // Trùng password
                return new UsernamePasswordAuthenticationToken(email, users.getPassword(), new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Kiểu hỗ trợ so sánh chứng thực cho AuthenticationManager
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
