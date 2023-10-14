package com.fosu.test;

import com.fosu.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestToken {

    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test(){
        //生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }

    @Test
    public void testToken(){
        boolean expiration = jwtHelper.isExpiration("eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNLM0N7QwMzUy1VEqLU4t8kwBitUCAHQoJ0UvAAAA.BeDoQudZtMyzgx-T7_ZfcvqO2pjOiwtqw80VcPSCuw4mNVU5Vm6B-1hkIKHkvzKziHMz5p_Xpy9QtsvFYL4coA");
        System.out.println(expiration);
    }
}
