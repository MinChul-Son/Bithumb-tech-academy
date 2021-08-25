package net.minchul.api.security.aop;

import lombok.RequiredArgsConstructor;
import net.minchul.api.security.domain.SecurityProvider;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    /**
     *  provider -> 서버쪽에서 jwt 토큰을 발급해서 프론트로 전달해줌. 이를 통해 통신
     *  filter를 통해 jwt 토큰이 맞는지 검증
     */

    private final SecurityProvider provider; 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = provider
    }
}
