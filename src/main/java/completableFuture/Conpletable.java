package completableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author huangjun
 * @version V1.0
 * @Description: TODO
 * @Date Create in 11:20 2019/10/08
 */
public class Conpletable {

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        Conpletable test = new Conpletable();
        // 结果集
        List<String> list = new ArrayList<>();

        List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> test.calc(integer))
                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfs).join();

        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
    }

    public int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
