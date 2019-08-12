package site.dongxiaoxu.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class SimpleTask extends RecursiveTask<Integer> {

    private final int THRESHOLD = 100;

    public SimpleTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private final int start;

    private final int end;

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            SimpleTask task1 = new SimpleTask(start, mid);
            SimpleTask task2 = new SimpleTask(mid, end);
            task1.fork();
            task2.fork();
            int result;
            result =  task1.join() + task2.join();

            System.out.println(Thread.currentThread().getName() + ":" + result);

        }


        return sum;
    }
}
