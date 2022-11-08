package rikkei.academy.controller.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.song.Category;
import rikkei.academy.model.User;
import rikkei.academy.security.userprincipal.UserDetailServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private UserDetailServiceIMPL userDetailServiceIMPL;
    @GetMapping
    public ResponseEntity<?> getList(Pageable pageable){
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }
    @PostMapping
    public ResponseEntity<?> createCategory(
            @RequestBody
            Category category
    ){
        User currentUser = userDetailServiceIMPL.getCurrentUser();
        category.setUser(currentUser);
        categoryService.save(category);
        return new  ResponseEntity<>(new ResponseMessage("create"), CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id")Category category
    ){
        return category== null ? new ResponseEntity<>(NOT_FOUND) : ResponseEntity.ok(category);
    }
    @PutMapping("{id}")
public ResponseEntity<?> updateCategory(
        @PathVariable("id")Category oldCategory,
        @RequestBody Category newCategory
    ){
    if (oldCategory==null){
    return new ResponseEntity<>(NOT_FOUND);
    }
    oldCategory.setName(newCategory.getName());
    categoryService.save(oldCategory);
    return ResponseEntity.ok(oldCategory);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable("id") Category category
    ){
        if (category == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        categoryService.deleteById(category.getId());
        return ResponseEntity.ok(new ResponseMessage("delete"));
    }
}