package com.jackasher.ware_manager.controller;

import com.google.code.kaptcha.Producer;
import com.jackasher.ware_manager.entity.LoginUser;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.entity.User;
import com.jackasher.ware_manager.service.UserService;
import com.jackasher.ware_manager.utils.CurrentUser;
import com.jackasher.ware_manager.utils.DigestUtil;
import com.jackasher.ware_manager.utils.TokenUtils;
import com.jackasher.ware_manager.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {

        String verificationCode = loginUser.getVerificationCode();
        if(!stringRedisTemplate.hasKey(verificationCode)){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码错误");
        }



        User user = userService.queryUserByCode(loginUser.getUserCode());
        if (user != null){
            if (user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)) {
                String userPwd = loginUser.getUserPwd();

                userPwd= DigestUtil.hmacSign(userPwd);

                if (userPwd.equals(user.getUserPwd())) {
                    CurrentUser currentUser = new CurrentUser(user.getUserId(), user.getUserCode(), user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, userPwd);

                    System.out.println("LoginToken: " +  token + "@" + currentUser + "@" + userPwd + "@" + user);

                    return Result.ok("登陆成功",token);

                }else {
                    return Result.err(Result.CODE_ERR_BUSINESS,"密码错误");
                }
            }else {
                return Result.err(Result.CODE_ERR_BUSINESS,"用户未审核");
            }
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"用户不存在");
        }
    }

    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        System.out.println("token: " + token);
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        System.out.println("currentUser: " + currentUser);
        return Result.ok(currentUser);
    }

}
