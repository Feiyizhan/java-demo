package io.github.feiyizhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableTransactionManagement
@ServletComponentScan
//@MapperScan(value = "io.github.feiyizhan.dao")
//@EnableScheduling
//@EnableAsync
//@EnableCaching
//@EnableWebSocket
@ComponentScan(basePackages = {"io.github.feiyizhan,io.github.feiyizhan.commons"})
public class SpringBootWebsocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebsocketDemoApplication.class, args);
	}

}
