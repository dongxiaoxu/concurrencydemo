package site.dongxiaoxu.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(6);

        ForkJoinTask<Integer> result = pool.submit(new SimpleTask(0, 5));

        try {
            Integer countResult = result.get();
            System.out.println(countResult);
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
