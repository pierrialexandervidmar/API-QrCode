package com.pierri.qrcode.services;

import java.util.Optional;
import java.util.UUID;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.pierri.qrcode.dtos.qrcode.QrCodeGenerateResponse;
import com.pierri.qrcode.ports.StoragePort;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Serviço responsável pela geração de QR Codes e upload para um storage
 * externo.
 * Usa a biblioteca ZXing para criar o QR Code em PNG e delega o envio ao
 * {@link StoragePort}.
 */
@Service
public class QrCodeGeneratorService {

    private final StoragePort storage;

    /**
     * Injeta a dependência do storage onde os arquivos serão armazenados.
     *
     * @param storage implementação de {@link StoragePort} para upload do QR Code
     */
    public QrCodeGeneratorService(StoragePort storage) {
        this.storage = storage;
    }

    /**
     * Gera um QR Code em PNG a partir de um texto, envia para o storage
     * e retorna a URL pública ou de acesso ao arquivo.
     *
     * @param text texto que será codificado no QR Code
     * @return objeto com a URL de acesso ao QR Code gerado
     * @throws WriterException erro na geração do QR Code
     * @throws IOException     erro na escrita em stream
     */
    public Optional<QrCodeGenerateResponse> generateUploadQrCode(String text) throws WriterException, IOException {
        try {
            // Cria o QR Code como matriz de bits
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

            // Converte a matriz para PNG em bytes
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngQrCodeData = pngOutputStream.toByteArray();

            // Envia para o storage e obtém a URL
            String url = storage.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "image/png");

            return Optional.of(new QrCodeGenerateResponse(url));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
