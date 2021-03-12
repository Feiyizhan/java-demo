package io.github.feiyizhan;

import org.junit.jupiter.api.Test;

/**
 * Text Blocks 测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class TextBlocksTests {

    
    @Test
    public void test1(){
        String str = """
            339:	Edwards-Curve Digital Signature Algorithm (EdDSA)
            360:	Sealed Classes (Preview)
            371:	Hidden Classes
            372:	Remove the Nashorn JavaScript Engine
            373:	Reimplement the Legacy DatagramSocket API
            374:	Disable and Deprecate Biased Locking
            375:	Pattern Matching for instanceof (Second Preview)
            377:	ZGC: A Scalable Low-Latency Garbage Collector
            378:	Text Blocks
            379:	Shenandoah: A Low-Pause-Time Garbage Collector
            381:	Remove the Solaris and SPARC Ports
            383:	Foreign-Memory Access API (Second Incubator)
            384:	Records (Second Preview)
            385:	Deprecate RMI Activation for Removal
            """;
        System.out.println(str);
    }
}
