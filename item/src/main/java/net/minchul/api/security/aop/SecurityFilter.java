package net.minchul.api.security.aop;

import lombok.RequiredArgsConstructor;
import net.minchul.api.security.domain.SecurityProvider;
import net.minchul.api.security.exception.SecurityRuntimeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private SecurityProvider provider;

    public SecurityFilter(SecurityProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = provider.resolveToken(httpServletRequest);
        try {
            if (token != null && provider.validateToken(token)) {
                Authentication auth = provider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (SecurityRuntimeException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
