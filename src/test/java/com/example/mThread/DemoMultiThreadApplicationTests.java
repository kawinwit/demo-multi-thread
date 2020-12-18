package com.example.mThread;

import com.example.mThread.service.CallBackChainService;
import com.example.mThread.service.ExceptionHandelParallelService;
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

    @Autowired
    ExceptionHandelParallelService exceptionHandelParallelService;

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
        exceptionHandelParallelService.example();
    }

}
