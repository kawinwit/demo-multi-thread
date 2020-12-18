package com.example.mThread.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class CallBackChainService {
    public void chain(){
        Executor executor = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start -> "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        },executor ).thenApply(response->{
            System.out.println("1 -> "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).thenRunAsync(() -> {
            System.out.println("runAsync -> "+Thread.currentThread().getName());

        },executor);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end -> "+Thread.currentThread().getName());
    }
}
