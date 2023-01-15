package uz.linuxuzbekistan.controller;
//Project name : app-linux-uzbekistan
//Time         : 5:44 PM


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.linuxuzbekistan.dto.AttachDTO;
import uz.linuxuzbekistan.service.AttachService;

@RestController
@Api(tags = "Attach controller")
@RequestMapping("/api/v1/attach")
public class AttachController {



    @Autowired
    private AttachService attachService;


    @PostMapping("/upload")
    @ApiOperation(value = "Api for upload file" ,nickname = "Upload FILE API" ,notes = "upload file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")

    })
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {

        AttachDTO upload = attachService.upload(file);
        return ResponseEntity.ok(upload);
    }












}



