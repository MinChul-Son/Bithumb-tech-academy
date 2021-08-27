package net.minchul.api.security.config;

import lombok.RequiredArgsConstructor;
import net.minchul.api.security.domain.SecurityProvider;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * web에서 넘어오는 부분에 대한 Security
 */

@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProvider provider;

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "*/**")
                .antMatchers("/", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 무상태
        http.authorizeRequests()
                .antMatchers("/users/signin").permitAll() // 토큰이 없어도 접속할 수 있는 url 설정
                .antMatchers("/users/signup").permitAll()
                .antMatchers("/users/findAll").permitAll()
                .antMatchers("/admin/access").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/login");
        http.apply(new SecurityConfig(provider));
    }
}
