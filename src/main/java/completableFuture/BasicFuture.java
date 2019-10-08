package completableFuture;

import java.util.concurrent.*;

/**
 * @author huangjun
 * @version V1.0
 * @Description: TODO
 * @Date Create in 9:36 2019/10/08
 */
public class BasicFuture {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() ->{
            // 长时间的异步计算
            // ……
            // 然后返回结果
            return 100;
        });
//        while(!f.isDone())
//            ;
        f.get();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
//future.join();
        future.get();
    }
}
