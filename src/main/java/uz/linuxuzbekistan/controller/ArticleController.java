package uz.linuxuzbekistan.controller;
//Project name : app-linux-uzbekistan
//Time         : 9:08 AM


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.linuxuzbekistan.dto.ArticleCreateDTO;
import uz.linuxuzbekistan.dto.ArticleUpdateDTO;
import uz.linuxuzbekistan.service.ArticleService;

import javax.validation.Valid;

@RestController
@Api(tags = "Article controller")
@RequestMapping("/api/v1/article")
public class ArticleController {




    @Autowired
    private ArticleService articleService;


    @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    @PostMapping("/create")
    @ApiOperation(value = "Api for create article" ,nickname = "Article create" ,notes = "create article")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")
    })
    public ResponseEntity create(@Valid @RequestBody ArticleCreateDTO articleCreate){

        return articleService.create(articleCreate);
    }
   @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Api for update article" ,nickname = "Article update" ,notes = "update article")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")
    })
    public ResponseEntity update(@PathVariable("id")String id,@Valid @RequestBody ArticleUpdateDTO articleUpdate){

        return articleService.update(id,articleUpdate);
    }
  @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Api for delete article" ,nickname = "Article delete" ,notes = "delete article")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")
    })
    public ResponseEntity delete(@PathVariable("id")String id){

        return articleService.delete(id);
    }
    @PreAuthorize("hasRole('ROLE_PUBLISHER')")
    @DeleteMapping("/increaseViewCount/{id}")
    @ApiOperation(value = "Api for increase view count article" ,nickname = "Article increase view count" ,notes = "increase view count article")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Muvaffaqqiyatli"),
            @ApiResponse(code = 403, message = "Ruxsat yo'q "),
            @ApiResponse(code = 201, message = "Yaratildi "),
            @ApiResponse(code = 401, message = "Avtorizatsiyadan o'tilmagan "),
            @ApiResponse(code = 404, message = "Mavjud bo'lmagan API ")
    })
    public ResponseEntity increaseViewCount(@PathVariable("id")String id){

        return articleService.increaseViewCount(id);
    }


}



