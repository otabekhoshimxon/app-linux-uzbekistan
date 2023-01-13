package uz.linuxuzbekistan.controller;
//Project name : app-linux-uzbekistan
//Time         : 11:34 PM


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.linuxuzbekistan.dto.CategoryCreateDTO;
import uz.linuxuzbekistan.dto.CategoryUpdateDTO;
import uz.linuxuzbekistan.service.CategoryService;

import javax.validation.Valid;

@RestController
@Api(tags = "Category controller")
@RequestMapping("/api/v1/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    @ApiOperation(value = "Api for create category" ,nickname = "Create category API" ,notes = "Create category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity getAll (@Valid @RequestBody CategoryCreateDTO categoryCreate){

        return categoryService.create(categoryCreate);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Api for create category" ,nickname = "Create category API" ,notes = "Create category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity getAll (@PathVariable("id") String id,@Valid @RequestBody CategoryUpdateDTO categoryUpdate){

        return categoryService.update(id,categoryUpdate);
    }







}



