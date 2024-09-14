package com.jackasher.ware_manager;

import com.jackasher.ware_manager.utils.CurrentUser;
import com.jackasher.ware_manager.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WareManagerApplicationTests {

    @Autowired
    private TokenUtils tokenUtils;

    @Test
    void tokenUtilTest() {
        String token = tokenUtils.loginSign(new CurrentUser(1, "jack", "Leo"), "casio233.");
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);

    }

}
