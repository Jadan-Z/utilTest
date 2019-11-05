package com.jadan.demo.controller;

import com.jadan.demo.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Jadan-Z
 * @Date 2019/11/5
 */
@RestController
public class QRCodeController {

    // 用于测试以流的方式输出qrcode
    @GetMapping("/qrCode")
    public void outPutQRCode(HttpServletResponse response) throws IOException {
        String url = "http://wx.fzminben.com/m/p/ticketPay.html" + "?recharge=" + "0.01" + "&name=" + "众包雇主充值";
        QRCodeUtil.outPutQRCode(response, url);
    }
}
