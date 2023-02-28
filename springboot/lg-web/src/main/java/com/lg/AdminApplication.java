package com.lg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liuga
 * @date ${YEAR}/${MONTH}/${DAY} ${TIME}
 * Description:
 */
@SpringBootApplication
//开启swagger
@EnableSwagger2
@EnableOpenApi
//@MapperScan("com.lg.sys.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        System.out.println("http://localhost:8080/doc.html");
        SpringApplication.run(AdminApplication.class, args);
    }
}
