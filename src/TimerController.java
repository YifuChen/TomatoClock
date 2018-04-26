import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimerController extends JPanel {

    private ClockFace cf;
    private String controllerName;
    private int currentSpan;
    private JLabel nameLabel;
    private JLabel timeLabel;

    private MyButton addButton;
    private MyButton minusButton;
    private boolean changeFace;

    public TimerController(ClockFace cf, String controllerName, int defaultTime, boolean changeFace){
        this.cf = cf;
        this.controllerName = controllerName;
        this.currentSpan = defaultTime;
        this.changeFace = changeFace;

        this.nameLabel = new JLabel("- " + controllerName + " -");
        this.timeLabel = new JLabel(defaultTime + " min");

        this.addButton = new MyButton("+", 30, 30);
        this.minusButton = new MyButton("-", 30, 30);

        initListener();
    }

    public MyButton getAddButton() {
        return this.addButton;
    }

    public MyButton getMinusButton() {
        return this.minusButton;
    }

    private void initListener() {
        this.addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentSpan++;
                timeLabel.setText(Integer.toString(currentSpan) + " min");
            }
        });

        this.minusButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentSpan--;
                timeLabel.setText(Integer.toString(currentSpan) + " min");
            }
        });
    }

    public int getCurrentSpan() {
        return this.currentSpan;
    }


    public JPanel getDefaultPanel() {
        Color textColor = new Color(218, 218, 219);
        Color bgColor = new Color(33,33,33);

        this.setLayout(new BorderLayout());
        this.setBackground(bgColor);
        this.nameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.nameLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        this.nameLabel.setForeground(textColor);
        this.add(nameLabel, BorderLayout.PAGE_START);

        this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
        this.timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.timeLabel.setForeground(textColor);
        this.timeLabel.setPreferredSize(new Dimension(150, 60));
        this.add(timeLabel, BorderLayout.CENTER);

        this.addButton.setFont("Arial", 20, textColor);
        this.minusButton.setFont("Arial", 20, textColor);

        JPanel buttonLayer = new JPanel();
        buttonLayer.setLayout(new GridLayout(1,2));

        buttonLayer.add(addButton);
        buttonLayer.add(minusButton);
        this.add(buttonLayer, BorderLayout.PAGE_END);

        this.setPreferredSize(new Dimension(150,130));

        return this;
    }

    public static void main(String args[]) {

        TimerController tc = new TimerController(new ClockFace(), "session", 25, true);
        JFrame window = new JFrame("Controller");


        window.setContentPane(tc.getDefaultPanel());
        window.setLocation(100,50);
        window.setPreferredSize(new Dimension(150, 130));
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
    }
}
