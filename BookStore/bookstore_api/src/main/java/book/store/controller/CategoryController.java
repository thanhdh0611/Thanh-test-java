package book.store.controller;

import book.store.payload.response.CategoryRsp;
import book.store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryRsp> categoryRsp = iCategoryService.getAllCategory();
        return new ResponseEntity<>(categoryRsp, HttpStatus.OK);
    }
}
