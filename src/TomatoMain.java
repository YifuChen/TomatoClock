import javax.swing.*;

public class TomatoMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Photo Stamper");
        TomatoUI content = new TomatoUI();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(800,500);
        window.setVisible(true);
    }
}
