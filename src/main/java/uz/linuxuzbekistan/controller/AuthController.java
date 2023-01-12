package uz.linuxuzbekistan.controller;
//Project name : app-linux-uzbekistan
//Time         : 7:06 PM


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.linuxuzbekistan.dto.AuthDTO;
import uz.linuxuzbekistan.service.AuthService;

@RestController
@Api(tags = "Authorization api")
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @ApiOperation(value = "Api for login" ,nickname = "Login API" ,notes = "login")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")

    })

    public String login(@RequestBody AuthDTO auth){

        String login = authService.login(auth);
        return login;

    }





}



