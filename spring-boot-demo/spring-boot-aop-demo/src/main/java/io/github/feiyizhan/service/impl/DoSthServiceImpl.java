package io.github.feiyizhan.service.impl;

import io.github.feiyizhan.service.IDoSthService;
import org.springframework.stereotype.Service;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@Service
public class DoSthServiceImpl implements IDoSthService {

    @Override
    public void doSth() {
        System.out.println("do sth ....");
    }
}
