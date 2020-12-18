package com.example.mThread;

import com.example.mThread.service.CallBackChainService;
import com.example.mThread.service.ParallelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class DemoMultiThreadApplicationTests {

    @Autowired
    ParallelService parallelService;

    @Autowired
    CallBackChainService callBackChainService;

    @Test
    void combineService() {
        parallelService.getSnacksForMovieCombine();

    }

    @Test
    void allOfService() {
        parallelService.allOfTest();

    }

    @Test
    void asyncChainService() {
        callBackChainService.chain();
    }

    @Test
    void exampleException() {
        Integer amount = null;
        CompletableFuture<Double> completableFuturePrice = CompletableFuture.supplyAsync(() -> {
            if (amount < 0) {
				throw new RuntimeException("test");
			} else {
                return amount * 111.11;
            }
        }).handle((response, ex) -> {
            if (ex != null) {
                //find cause of exception
                while (ex instanceof RuntimeException && Objects.nonNull(ex.getCause())) {
                	ex = ex.getCause();
					System.out.println(ex.getMessage());
                }

                return 0.00;
            } else {
                System.out.println("Success");
                return response;
            }
        });
        System.out.println("$" + completableFuturePrice.join());
    }

}
