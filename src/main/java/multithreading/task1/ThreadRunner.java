package multithreading.task1;

public class ThreadRunner extends Thread{

    public ThreadRunner(ThreadGroup group, String name){
        super(group, name);
    }

    @Override
    public void run(){
        long tBeg = System.currentTimeMillis();
        while (true) {
            System.out.println(this.getName() + " is still running!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                long tSpend = (System.currentTimeMillis() - tBeg) / 1000;
                System.out.println(this.getName() + " was working for " + tSpend + " seconds");
                return;
            }
        }
    }
}
