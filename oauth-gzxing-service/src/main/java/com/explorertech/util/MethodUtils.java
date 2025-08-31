package com.explorertech.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class MethodUtils {

    public static void generateImageQRCode(String text, int width, int height, String path){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try{
            BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix, "PNG", FileSystems.getDefault().getPath(path));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] generateByteQRCode(String text, int width, int height){
        ByteArrayOutputStream byteArrayOutputStream = null;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try{
            BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
            MatrixToImageWriter.writeToStream(matrix, "PNG", byteArrayOutputStream, config);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
