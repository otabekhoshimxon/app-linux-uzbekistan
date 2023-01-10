package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.config.CustomUserDetails;
import uz.linuxuzbekistan.dto.AuthDTO;
import uz.linuxuzbekistan.dto.ResponceDTO;
import uz.linuxuzbekistan.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileService profileService;
    public ResponseEntity login(AuthDTO auth) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getPhone(), auth.getPassword()));
        CustomUserDetails principal = (CustomUserDetails) authenticate.getPrincipal();

        String bearer="Bearer ";

        if (profileService.existsById(principal.getId())) {
            return ResponceDTO.sendAuthorizationToken(principal.getUsername(), bearer+ JwtUtil.encodeId(principal.getId()));
        }

        return ResponseEntity.badRequest().body("Phone or password is incorrect.");
    }
}
