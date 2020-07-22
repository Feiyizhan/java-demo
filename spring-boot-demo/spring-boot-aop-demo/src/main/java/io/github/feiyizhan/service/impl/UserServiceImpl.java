package io.github.feiyizhan.service.impl;

import io.github.feiyizhan.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public void testIntroduction() {
        System.out.println("do testIntroduction");
    }
}
