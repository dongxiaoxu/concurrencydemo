package site.dongxiaoxu.concurrency.future;

/**
 * Created by dongxiaoxu on 2019/8/5.
 */
public interface Future<T> {
    T get();
}
