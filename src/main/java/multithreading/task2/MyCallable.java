package multithreading.task2;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<AnswerRec> {

    private String threadName;
    private int repeat;
    MyCallable(String threadName){
        this.threadName = threadName;
        this.repeat = 0;
    }
    @Override
    public AnswerRec call() throws Exception {
        Thread.currentThread().setName(threadName);
        try {


            while (Thread.currentThread().isAlive() && repeat < 5) {
                System.out.println(Thread.currentThread().getName() + " is running...");
                Thread.sleep(2000);
                repeat++;
            }
        } catch (InterruptedException e){
            return new AnswerRec(repeat, Thread.currentThread().getName());
        }
        return new AnswerRec(repeat, Thread.currentThread().getName());
    }
}
