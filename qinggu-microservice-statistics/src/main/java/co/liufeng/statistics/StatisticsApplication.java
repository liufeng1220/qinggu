package co.liufeng.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rafeily
 * @date 2019/11/13 16:49
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"co.liufeng.statistics","co.liufeng.common"})
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
