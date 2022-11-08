package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rikkei.academy.repository.IUserRepository;
import rikkei.academy.service.user.IUSerService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    IUSerService uSerService;
//    @Autowired
//    IUserRepository userRepository;
    @GetMapping("/listuser")
    public ResponseEntity<?> listUser(){
        return ResponseEntity.ok(uSerService.findAll());
    }
}
