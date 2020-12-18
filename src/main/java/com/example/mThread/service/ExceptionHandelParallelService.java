package com.example.mThread.service;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class ExceptionHandelParallelService {
    public void example(){
        Integer amount = 1;
        CompletableFuture<Double> completableFuturePrice = CompletableFuture.supplyAsync(() -> {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount can not be negative");
            } else {
                return amount * 111.11;
            }
        }).handle((response, ex) -> {
            if (ex != null) {
                //ตรงนี้สำคัญมากนะครับ คือการล้วงเอา cause ที่แท้จริงของ exception ออกมา
                while ( ex instanceof RuntimeException &&
                        Objects.nonNull(ex.getCause())
                ) { ex = ex.getCause(); }

                return 0.00;
            } else {
                System.out.println("Success");
                return response;
            }
        });
        System.out.println("$" + completableFuturePrice.join());
    }
}
