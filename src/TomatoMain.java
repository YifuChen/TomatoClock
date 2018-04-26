import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TomatoMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tomato Clock");
        TomatoUI content = new TomatoUI();
        window.setContentPane(content);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setVisible(true);
    }
}