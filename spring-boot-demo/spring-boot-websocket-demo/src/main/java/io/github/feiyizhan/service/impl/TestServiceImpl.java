package io.github.feiyizhan.service.impl;

import io.github.feiyizhan.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @author 徐明龙 XuMingLong 2019-12-03
 */
@Service
public class TestServiceImpl implements ITestService {
    /**
     * 显示内容
     * @author 徐明龙 XuMingLong 2019-12-03
     * @param content
     * @return java.lang.String
     */
    @Override public String echo(String content) {
        return content;
    }
}
