package com.jadan.demo.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具: 该工具用于根据链接生成一个二维码，使得设备扫码后可以直接访问链接
 <!-- 生成二维码依赖 -->
 <dependency>
 <groupId>com.google.zxing</groupId>
 <artifactId>javase</artifactId>
 <version>3.3.3</version>
 </dependency>
 */
public class QRCodeUtil {
    // 设定二维码的宽度
    private static int width = 400;
    // 设定二维码的高度
    private static int height = 400;
    // 设定二维码的图片格式
    private static String format = "png";

    /**
     * 以流的形式输出二维码
     * @param response
     * @param url
     * @throws IOException
     */
    public static void outPutQRCode(HttpServletResponse response, String url) throws IOException {
        OutputStream os = null;
        try {
            BitMatrix bitMatrix = generateQRCodeByUrl(url);
            os = response.getOutputStream();
            //至此，一个二维码图像就生成完毕了，或者也可以使用下面的方法将其写出至输出流中
            MatrixToImageWriter.writeToStream(bitMatrix, format, os);
            os.flush();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * 以生成图片并存入文件夹
     * @param url
     * @param dir
     * @param name
     */
    public static void outPutQRCode(String url, String dir, String name) {
        try {
            BitMatrix bitMatrix = generateQRCodeByUrl(url);
            Path file = new File(dir, name + ".png").toPath();
            //使用默认设置、将bit封装对象以指定的图像格式、写入至指定文件中
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据url生成二维码
     * @param url
     * @return
     * @throws WriterException
     */
    private static BitMatrix generateQRCodeByUrl(String url) throws WriterException {
        //对二维码进行必要的设定
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设定内容的编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设定图像的错误校正程度：
        //M - ~15%
        //L - ~7%
        //H - ~30%
        //Q - ~25%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设定图像外边距（像素）
        hints.put(EncodeHintType.MARGIN, 2);
        //进行编码并获得一个bit封装对象
        return new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
    }



}
