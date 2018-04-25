public class Task {
    private boolean done;
    private String task;
    private int expected;
    private int completed;

    public Task(boolean done, String task, int expected, int completed) {
        this.done = done;
        this.task = task;
        this.expected = expected;
        this.completed = completed;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public boolean isDone() {
        return done;
    }

    public String getTask() {
        return task;
    }

    public int getExpected() {
        return expected;
    }

    public int getCompleted() {
        return completed;
    }
}
