package multithreading.task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("Group");
        for (int i = 0; i < 4; i++) {
            ThreadRunner runner = new ThreadRunner(threadGroup, "Thread number " + i);
            runner.start();
        }
        Thread.sleep(15_000);
        threadGroup.interrupt();

    }
}
