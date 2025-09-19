package com.pierri.qrcode.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pierri.qrcode.dtos.qrcode.QrCodeGenerateRequest;
import com.pierri.qrcode.dtos.qrcode.QrCodeGenerateResponse;

@RestController
@RequestMapping("/qrcode")
public class QrCodeResource {

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> qrCodeGenerator(@RequestBody QrCodeGenerateRequest request) {
        return ResponseEntity.ok().build();        
    }

}
