package com.jackasher.ware_manager.controller;

import com.google.code.kaptcha.Producer;
import com.jackasher.ware_manager.entity.LoginUser;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.entity.User;
import com.jackasher.ware_manager.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Jackasher
 * @version 1.0
 * @className LoginController
 * @since 1.0
 **/

@RestController
public class LoginController {

    @Resource(name = "captchaProducer")
    private Producer producer;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse httpServletResponse) throws IOException {
        String text = producer.createText();

        BufferedImage image = producer.createImage(text);

        stringRedisTemplate.opsForValue().set(text, "", 60 * 30, TimeUnit.SECONDS);

        httpServletResponse.setContentType("image/jpeg");

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        ImageIO.write(image, "jpg", outputStream);

        outputStream.flush();

    }

    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {

        System.out.println("Login executed!");
        User user = userService.queryUserByCode(loginUser.getUserCode());
        System.out.println("/login执行" + user);
        return null;
    }

}
