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
    private boolean changeFace;

    public TimerController(ClockFace cf, String controllerName, int defaultTime, boolean changeFace){
        setLayout(new GridLayout(3,1));
        this.cf = cf;
        this.controllerName = controllerName;
        this.currentTime = defaultTime;
        this.changeFace = changeFace;
        nameLable = new JLabel(controllerName);
        nameLable.setHorizontalAlignment(JLabel.CENTER);
        nameLable.setFont(new Font("Serif", Font.BOLD, 28));
        add(nameLable);
        String defaultShowTime = defaultTime + " min";
        timeLable = new JLabel(defaultShowTime);
        timeLable.setHorizontalAlignment(JLabel.CENTER);
        add(timeLable);
        buttonLayer = new JPanel();
        buttonLayer.setLayout(new GridLayout(1,2));
        addButton = new JButton("+");
        minusButton = new JButton("-");
        buttonLayer.add(addButton);
        buttonLayer.add(minusButton);
        add(buttonLayer);
        addListener();
    }

    private void addListener() {
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime++;
                String passTime = currentTime + ":" +"00";
                if (changeFace) {
                    cf.setTime(passTime);
                }
                timeLable.setText(Integer.toString(currentTime) + " min");
            }
        });

        this.minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime--;
                String passTime = currentTime + ":" +"00";
                if (changeFace) {
                    cf.setTime(passTime);
                }
                timeLable.setText(Integer.toString(currentTime) + " min");
            }
        });
    }

    public int timeGetter() {
        return this.currentTime;
    }
}
