package com.jadan.demo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Jadan-Z
 * @Date 2019/11/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QRCodeUtilTests {

    MockHttpServletResponse response;


    @Test
    public void test1() throws IOException {
        response = new MockHttpServletResponse();
        String url = "http://wx.fzminben.com/m/p/ticketPay.html" + "?recharge=" + "0.01" + "&name=" + "众包雇主充值";
        QRCodeUtil.outPutQRCode(response, url);
    }

    @Test
    public void test2() {
        String url = "http://wx.fzminben.com/m/p/ticketPay.html" + "?recharge=" + "0.01" + "&name=" + "众包雇主充值";
        QRCodeUtil.outPutQRCode(url, "C://Jadan-project//utilTest", "src");
    }
}
