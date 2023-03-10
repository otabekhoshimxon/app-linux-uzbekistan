package uz.linuxuzbekistan.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.linuxuzbekistan.config.CustomUserDetails;

@Component
public class CurrentUserUtil {

    public CustomUserDetails getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();

    }
}
