package com.lg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuga
 * @date 2023/02/01 8:36
 * Description:
 */
@SpringBootTest(classes = TestApplication.class)
public class TestApplication {
    /**
     * 线程池测试
     */
    @Test
    void test1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));


        for (int i = 0; i < executor.getCorePoolSize(); i++) {
            executor.execute(() -> {

                        System.out.println(Thread.currentThread().getName());
                    }
                );
        }
    }
}
