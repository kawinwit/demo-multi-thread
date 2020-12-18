package com.example.mThread.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParallelService {

    CompletableFuture<Integer> getPopCorn(Executor executor) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("pop - " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (1);
        }, executor);
        return future;
    }

    CompletableFuture<Integer> getDrink(Executor executor) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            try {
                System.out.println("drink - " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return (2);
        }, executor);
        return future;
    }

    //snacks are ready when popcorn and drink are ready
    public void getSnacksForMovieCombine() {
        System.out.println("main - " + Thread.currentThread().getName());
        Executor executor = Executors.newFixedThreadPool(2);
        //start getPopFirst andThen getDrink start after but not wait getPopDone
        CompletableFuture<Integer> snacks = getPopCorn(executor)
                .thenCombine(getDrink(executor), (pop, drink) -> {
                    System.out.println("snack - " + Thread.currentThread().getName());
                    return pop + drink;
                });

        System.out.println(snacks.join());
        //Start Working parallel and wail all task done to join;


    }

    public void allOfTest() {
        Executor executor = Executors.newFixedThreadPool(4);
        //return finish first response
        CompletableFuture<Object> combinedAny = CompletableFuture.anyOf(getStingFirst(executor), getStringSecond(executor), getStringThird(executor));
        combinedAny.join();
        //void return Type off allOf
        //allOf only makeSure start parallel
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(getStingFirst(executor), getStringSecond(executor), getStringThird(executor));
        combinedFuture.join();

        //if want response
        //use stream api of java 8
        String combined = Stream.of(getStingFirst(executor), getStringSecond(executor), getStringThird(executor))
                .map(CompletableFuture::join)
                .map(o->o.toString())
                .collect(Collectors.joining(" "));
        System.out.println(combined);

    }

    private CompletableFuture<String> getStingFirst(Executor executor) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Hello";
        }, executor);
        return future1;
    }

    private CompletableFuture<String> getStringSecond(Executor executor) {
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "Beautiful";
        }, executor);
        return future2;
    }


    private CompletableFuture<String> getStringThird(Executor executor) {
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "World";
        }, executor);
        return future3;
    }


}


