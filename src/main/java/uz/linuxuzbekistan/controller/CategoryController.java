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
    public ResponseEntity create (@Valid @RequestBody CategoryCreateDTO categoryCreate){

        return categoryService.create(categoryCreate);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Api for update category" ,nickname = "Update category API" ,notes = "Update category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity update (@PathVariable("id") String id,@Valid @RequestBody CategoryUpdateDTO categoryUpdate){

        return categoryService.update(id,categoryUpdate);
    }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Api for delete category" ,nickname = "Delete category API" ,notes = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity delete (@PathVariable("id") String id){

        return categoryService.delete(id);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/getAll")
    @ApiOperation(value = "Api for get ALL CATEGORIES" ,nickname = "GET all categories API" ,notes = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity getAll (){

        return categoryService.getAllCategories();
    }
  @PreAuthorize("permitAll()")
    @GetMapping("/getByKey/{key}")
    @ApiOperation(value = "Api for get  CATEGORY by KEY" ,nickname = "GET  category by KEY API" ,notes = "Get category by KEY")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MUVAFFAQQIYATLI"),
            @ApiResponse(code = 403, message = "RUXSAT YO'Q "),
            @ApiResponse(code = 201, message = "YARATILDI "),
            @ApiResponse(code = 401, message = "AVTORIZATSIYADAN O'TILMAGAN "),
            @ApiResponse(code = 404, message = "MAVJUD BO'LMAGAN SAHIFA ")
    })
    public ResponseEntity getByKey (@PathVariable("key") String key){

        return categoryService.getByKey(key);
    }








}



