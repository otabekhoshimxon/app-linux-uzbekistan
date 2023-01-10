package uz.linuxuzbekistan.config;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.linuxuzbekistan.repository.ProfileRepository;
import uz.linuxuzbekistan.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Component

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = header.split(" ")[1];
            String id = JwtUtil.decodeId(token);
            CustomUserDetails customUserDetails=null;
            if (profileRepository.existsById(id)){
                customUserDetails=new CustomUserDetails(profileRepository.findById(id).get());
            }
            UsernamePasswordAuthenticationToken filter = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            filter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(filter);
            filterChain.doFilter(request, response);
        } catch (JwtException  | NullPointerException  e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message ", "Token expired time");
            System.err.println(e.getMessage());
        }
    }
}
