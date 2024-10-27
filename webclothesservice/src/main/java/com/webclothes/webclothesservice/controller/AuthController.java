package com.webclothes.webclothesservice.controller;

import com.webclothes.webclothesservice.dto.UserDto;
import com.webclothes.webclothesservice.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> regeister(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.login(userDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserDto> refreshToken(@RequestBody UserDto req){
        return ResponseEntity.ok(userService.refreshToken(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Đã đăng xuất thành công");
    }

}

