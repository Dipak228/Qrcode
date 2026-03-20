package com.example.Qrcode.util;

import java.io.File;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRcodeGenerator {

    public static void generateQR(String text, String path) throws Exception {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300);

        File file = new File(path);
        MatrixToImageWriter.writeToPath(matrix, "PNG", file.toPath());
    }
}