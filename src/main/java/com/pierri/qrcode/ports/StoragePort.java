package com.pierri.qrcode.ports;

public interface StoragePort {

    String uploadFile(byte[] file, String fileName, String contentType);

}
