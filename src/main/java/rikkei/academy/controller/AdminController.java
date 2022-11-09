package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rikkei.academy.model.User;
import rikkei.academy.repository.IUserRepository;
import rikkei.academy.service.user.IUSerService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    IUSerService uSerService;
//    @Autowired
//    IUserRepository userRepository;
    @GetMapping("/listuser")
    public ResponseEntity<?> ShowListUser(@PageableDefault(size = 5) Pageable pageable) {
        Page<User> users = uSerService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
