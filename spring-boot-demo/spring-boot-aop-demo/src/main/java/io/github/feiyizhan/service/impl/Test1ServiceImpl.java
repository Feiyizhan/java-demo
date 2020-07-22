package io.github.feiyizhan.service.impl;

import io.github.feiyizhan.service.ITest1Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@Service
public class Test1ServiceImpl implements ITest1Service {
    /**
     * 获取文本
     * @author 徐明龙 XuMingLong 2020-07-21
     * @param text
     * @return java.lang.String
     */
    @Override public String getText(String text) {
        return StringUtils.chop(text);
    }

    @Override public String sayHello(String name, String sex, String age) {
        return String.join(" ",name,sex,age);
    }
}
