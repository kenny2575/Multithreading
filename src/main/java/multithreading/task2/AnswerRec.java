package multithreading.task2;

public record AnswerRec(int repeats, String threadName) {

    @Override
    public String toString() {
        return "Output messages: " +
                repeats + ", thread: " + threadName;
    }
}
