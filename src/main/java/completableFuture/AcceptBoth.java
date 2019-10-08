package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author huangjun
 * @version V1.0
 * @Description: TODO
 * @Date Create in 10:09 2019/10/08
 */
public class AcceptBoth {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f =  future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f.get());

        CompletableFuture<Integer> future1= CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f1 =  future1.thenRun(() -> System.out.println("finished"));
        System.out.println(f1.get());


        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f2 =  future2.thenCompose( i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });
        System.out.println(f2.get()); //1000
        //并行执行
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f3 =  future3.thenCombine(future4, (x,y) -> y + "-" + x);
        System.out.println(f3.get()); //abc-100
    }
}
