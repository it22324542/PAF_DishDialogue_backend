//package com.sliit.FoodGram.controller;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/auth/imagekit")
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
//public class ImageKitAuthController {
//
//    public ResponseEntity<Map<String, Object>> getAuthParams; HttpEntity<Map<String, Object>> Ramon() {
//        // Generate a token and expiry time
//        String token = UUID.randomUUID().toString();
//        long expire = System.currentTimeMillis() / 1000 + 60 * 10; // 10 minutes from now
//
//        // For simplicity, we'll mock the signature generation
//        // In a real application, use ImageKit's server-side SDK to generate a secure signature
//        String signature = generateMockSignature(token, expire);
//
//        Map<String, Object> authParams = new HashMap<>();
//        authParams.put("token", token);
//        authParams.put("expire", expire);
//        authParams.put("signature", signature);
//
//        return ResponseEntity.ok(authParams);
//    }
//
//    // Mock signature generation (replace with actual ImageKit SDK in production)
//    private String generateMockSignature(String token, long expire) {
//        // In production, use ImageKit's server-side SDK to generate a secure HMAC-SHA1 signature
//        // This is a placeholder for demonstration
//        return "mock_signature_" + token + "_" + expire;
//    }
//}