package multithreading.task2;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
          Callable<AnswerRec> callService = () -> {
            int repeats = 0;
            for (int i = 0; i < 6; i++) {
                Thread.sleep(2000);
                System.out.println("Всем привет! Пишет вам " + Thread.currentThread().getName());
                repeats++;
            }
            return new AnswerRec(repeats, Thread.currentThread().getName());
        };

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final Future<AnswerRec> task = threadPool.submit(callService);
        final Future<AnswerRec> task1 = threadPool.submit(callService);
        final Future<AnswerRec> task2 = threadPool.submit(callService);
        final Future<AnswerRec> task3 = threadPool.submit(callService);

        System.out.println(task.get());
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());

        threadPool.shutdown();

        final ExecutorService threadPoolInvokeAll = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<AnswerRec>> list = threadPoolInvokeAll.invokeAll(
                List.of(new MyCallable("First thread"), new MyCallable("Second thread"), new MyCallable("Third thread"), new MyCallable("Fourth thread"))
        );

        for (Future<AnswerRec> answerRecFuture : list) {
            System.out.println(answerRecFuture.get());
        }
        threadPoolInvokeAll.shutdown();

        final ExecutorService threadPoolInvokeAny = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println(threadPoolInvokeAny.invokeAny(
                List.of(new MyCallable("First thread"), new MyCallable("Second thread"), new MyCallable("Third thread"), new MyCallable("Fourth thread"))
        ));


        threadPoolInvokeAny.shutdown();
    }
}
