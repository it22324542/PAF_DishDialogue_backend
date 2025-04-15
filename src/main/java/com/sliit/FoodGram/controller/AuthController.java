package com.sliit.FoodGram.controller;

import com.sliit.FoodGram.dto.LoginRequest;
import com.sliit.FoodGram.dto.LoginResponse;
import com.sliit.FoodGram.dto.RegisterRequest;
import com.sliit.FoodGram.model.User;
import com.sliit.FoodGram.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${imagekit.private-key}")
    private String IMAGEKIT_PRIVATE_KEY;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(request.password);

        authService.register(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            return authService.authenticate(request.email, request.password)
                    .map(user -> {
                        session.setAttribute("USER", user);
                        LoginResponse loginResponse = new LoginResponse(user.getId());
                        return ResponseEntity.ok(loginResponse);
                    })
                    .orElseGet(() -> ResponseEntity.status(401).body((LoginResponse) Map.of("error", "Invalid credentials")));
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return ResponseEntity.status(500).body(Map.of("error", "Server error during login"));
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/imagekit")
    public ResponseEntity<Map<String, String>> getImageKitAuth() {
        try {
            String token = UUID.randomUUID().toString();
            String expire = String.valueOf(System.currentTimeMillis() / 1000 + 3600);
            String signature = generateSignature(token, expire, IMAGEKIT_PRIVATE_KEY);

            Map<String, String> authParams = new HashMap<>();
            authParams.put("token", token);
            authParams.put("expire", expire);
            authParams.put("signature", signature);

            System.out.println("ImageKit auth params: " + authParams);
            return ResponseEntity.ok(authParams);
        } catch (Exception e) {
            System.err.println("Error generating ImageKit auth parameters: " + e.getMessage());
            return ResponseEntity.status(500).body(Map.of("error", "Failed to generate ImageKit auth parameters"));
        }
    }

    private String generateSignature(String token, String expire, String privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String data = token + expire;
        System.out.println("Signature input - Token: " + data);
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(
                privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        mac.init(keySpec);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String signature = hexString.toString();
        System.out.println("Generated signature: " + signature);
        return signature;
    }
}