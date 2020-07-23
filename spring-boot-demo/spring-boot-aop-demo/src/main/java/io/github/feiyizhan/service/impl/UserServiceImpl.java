package io.github.feiyizhan.service.impl;

import io.github.feiyizhan.annotation.Log;
import io.github.feiyizhan.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public String save(String user) {
        System.out.println("保存用户信息");
        if ("a".equals(user)) {
            throw new RuntimeException();
        }
        return user;
    }

    @Override
    public void testIntroduction() {
        System.out.println("do testIntroduction");
    }

    @Log(value = "test")
    @Override
    public void testAnnotationAop() {
        System.out.println("testAnnotationAop");
    }
}
