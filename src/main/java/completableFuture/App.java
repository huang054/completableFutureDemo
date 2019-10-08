package completableFuture;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //长时间的计算任务
            return "·00";
        });
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            return "hello";
        }, Executors.newSingleThreadExecutor());

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f =  future3.thenApplyAsync(i -> i * 10).thenApply(i -> i+"haha");
        System.out.println(f.get()); //"1000"


        CompletableFuture<Integer> future4= CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f1 =  future4.thenAccept(System.out::println);
        System.out.println(f1.get());
    }
}
