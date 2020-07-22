package io.github.feiyizhan.beans;

import io.github.feiyizhan.service.IDoSthService;
import io.github.feiyizhan.service.impl.DoSthServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IntroductionAop {

    /**
     * 定义一个给 io.github.feiyizhan 包下所有 service包下的类增加一个新的接口，并使用指定新的接口实现类
     * 这样这些被增强的类就都有了IDoSthService的所有方法，可以强制转换成IDoSthService 类，调用这些方法。
     * @author 徐明龙 XuMingLong 2020-07-21
     */
    @DeclareParents(value = "io.github.feiyizhan..service..*", defaultImpl = DoSthServiceImpl.class)
    public IDoSthService doSthService;

}
