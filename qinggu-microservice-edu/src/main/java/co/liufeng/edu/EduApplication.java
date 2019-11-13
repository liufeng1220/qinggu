package co.liufeng.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rafeily
 * @date 2019/10/30 16:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"co.liufeng.edu","co.liufeng.common"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
