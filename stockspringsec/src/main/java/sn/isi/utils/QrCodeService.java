package sn.isi.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class QrCodeService {

    public void generateQrCode(String data) throws WriterException, IOException {

        int size = 400;

        // encode
        BitMatrix bitMatrix = generateMatrix(data, size);

        String imageFormat = "png";
        String outputFileName = "/Users/macbookair/Documents/IJProjects/Spring/2022/M2IAGE/stockspringsec/codeQr/qrcode-sping." + imageFormat;

        // write in a file
        writeImage(outputFileName, imageFormat, bitMatrix);
    }
    private static BitMatrix generateMatrix(String data, int size) throws WriterException {
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        return bitMatrix;
    }

    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        fileOutputStream.close();
    }
}
