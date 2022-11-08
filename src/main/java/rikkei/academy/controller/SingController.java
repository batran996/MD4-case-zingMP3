package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.sing.Singer;
import rikkei.academy.service.sing.ISingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/singers")
public class SingController {
    @Autowired
    private ISingService singService;
    @GetMapping
    public ResponseEntity<?> listSinger(Pageable pageable){
        Page<Singer> singers = singService.findAll(pageable);
        return new ResponseEntity<>(singers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSinger(@Valid @RequestBody Singer singer){
return new ResponseEntity<>(HttpStatus.OK);


    }
}
