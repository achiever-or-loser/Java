package thread.threadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description
 * @Author shichao.chen
 * @Date 2019/9/12 17:34
 * @Version 1.0
 **/
public class MyMonitorThread implements Runnable {
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated()));
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}