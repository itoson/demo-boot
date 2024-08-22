package org.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController {

    @PostMapping("/send-sms")
    public ResponseEntity<Map<String, String>> sendSms(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String verificationCode = generateVerificationCode();

        String apiUrl = "https://www.sms-ope.com/sms/api/?&mobilenumber=" + phoneNumber
                + "&smstext=認証コードは" + verificationCode + "です。&method=1";

        String username = "eagle-tec-a";
        String password = "svRDLvY3ncij";

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        Map<String, String> response = new HashMap<>();
        response.put("result", result.getBody()); // result = 200 发送成功
        return ResponseEntity.ok(response);
    }

    private String generateVerificationCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 1000));
    }
}
