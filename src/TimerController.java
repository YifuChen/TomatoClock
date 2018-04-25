import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerController extends JPanel {
    public JPanel content;
    private ClockFace cf;
    private String controllerName;
    private int currentTime;
    private JLabel nameLable;
    private JLabel timeLable;
    private JPanel buttonLayer;
    private JButton addButton;
    private JButton minusButton;

    public TimerController(ClockFace cf, String controllerName, int defaultTime) {
        this.cf = cf;
        this.controllerName = controllerName;
        this.currentTime = defaultTime;
        content = new JPanel();
        content.setLayout(new GridLayout(3,1));
        nameLable = new JLabel(controllerName);
        content.add(nameLable);
        String defaultShowTime = defaultTime + " min";
        timeLable = new JLabel(defaultShowTime);
        content.add(timeLable);
        buttonLayer = new JPanel();
        buttonLayer.setLayout(new GridLayout(1,2));
        addButton = new JButton("+");
        minusButton = new JButton("-");
        buttonLayer.add(addButton);
        buttonLayer.add(minusButton);
        content.add(buttonLayer);
        addListener();
    }

    private void addListener() {
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime++;
                String passTime = currentTime + ":" +"00";
                cf.setTime(passTime);
            }
        });

        this.minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime--;
                String passTime = currentTime + ":" +"00";
                cf.setTime(passTime);
            }
        });
    }

    public int timeGetter() {
        return this.currentTime;
    }
}
