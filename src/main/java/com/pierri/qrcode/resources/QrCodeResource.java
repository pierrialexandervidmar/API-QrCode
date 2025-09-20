package com.pierri.qrcode.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pierri.qrcode.dtos.qrcode.QrCodeGenerateRequest;
import com.pierri.qrcode.dtos.qrcode.QrCodeGenerateResponse;
import com.pierri.qrcode.services.QrCodeGeneratorService;

@RestController
@RequestMapping("/qrcode")
public class QrCodeResource {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeResource(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> qrCodeGenerator(@RequestBody QrCodeGenerateRequest request) {

        try {
            return this.qrCodeGeneratorService.generateUploadQrCode(request.text())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
