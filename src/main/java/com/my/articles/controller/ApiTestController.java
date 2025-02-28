package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApiTestController {
    @GetMapping("/api")
    public String apiForm() {
        return "/test/api";
    }

    @PostMapping("apiPostTest")
    // Return type : ResponseEntity 이면 Object로 전송
    public ResponseEntity apiPostTest (@RequestBody LoginDTO dto) {
        String info = dto.getUserid() + " / " + dto.getPassword();
        System.out.println("=========info : " + info);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
}
