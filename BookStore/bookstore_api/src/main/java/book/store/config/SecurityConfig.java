package book.store.config;


import book.store.filter.JWTFilter;
import book.store.provider.CustomAuthenManagerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomAuthenManagerProvider authenManagerProvider;

    @Autowired
    private JWTFilter jwtFilter;

    // MD5, SHA1, RSA ...

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                        .authenticationProvider(authenManagerProvider)
                        .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        return http.csrf().disable()
                // không sử dụng session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/hello/**").permitAll()
                .antMatchers("/product/file/**").permitAll()
                .antMatchers("/about/**").permitAll()
                .antMatchers("/books/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .anyRequest().authenticated()
                //.and().httpBasic()// xài JWT nên phải bỏ cái này đi
                // UsernamePasswordAuthenticationFilter.class hình như này là sử dụng của authenticationManager
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}