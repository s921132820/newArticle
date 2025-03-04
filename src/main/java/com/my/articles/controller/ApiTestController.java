package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("getResponse")
    @ResponseBody
    public LoginDTO postResponse() {
        return new LoginDTO("안유진", "1234");
    }

    @PostMapping("apiPostArrayTest")
    public ResponseEntity<Map<String, String>> apiPostArrayTest(
            @RequestBody List<LoginDTO> dtos
            ) {
        dtos.stream().forEach(x -> System.out.println(x));
        Map<String, String> userData = new HashMap<>();
        userData.put("id", dtos.get(1).getUserid());
        userData.put("pw", dtos.get(1).getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

    @DeleteMapping("/apiTest")
    @ResponseBody
    public String apiHttpMethodTest() {
        String message = "Delete Mapping";
        return message;
    }
}
