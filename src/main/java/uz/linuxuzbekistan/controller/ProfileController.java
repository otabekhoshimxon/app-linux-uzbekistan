package uz.linuxuzbekistan.controller;
//Project name : app-linux-uzbekistan
//Time         : 11:55 PM


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.linuxuzbekistan.dto.ProfileCreateDTO;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.service.ProfileService;

@RestController
@Api(tags = "Profile controller")
@RequestMapping("/api/v1/profile")
public class ProfileController {


    @Autowired
    private ProfileService profileService;



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/moderator")
    @ApiOperation(value = "Api for create MODERATOR" ,nickname = "Create MODERATOR API" ,notes = "Create MODERATOR")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity createModerator (@RequestBody ProfileCreateDTO create){

         return profileService.create(create, GeneralRole.MODERATOR);
    }

 @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/publisher")
    @ApiOperation(value = "Api for create PUBLISHER" ,nickname = "Create PUBLISHER API" ,notes = "Create PUBLISHER")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity createPublisher (@RequestBody ProfileCreateDTO create){

         return profileService.create(create,GeneralRole.PUBLISHER);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    @ApiOperation(value = "Api for get all " ,nickname = "Get all profiles API" ,notes = "Get all profiles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity getAll (@RequestParam(value = "page" ,defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "4")int size){

         return profileService.getAll(page,size);
    }




}



