import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ClockFace {

    private JButton start;          // start button
    private JButton stop;           // stop button
    private JLabel time;            // text that display current time
    private JLabel status;          // text that display current status
    private BufferedImage img;      // img used to draw clock face
    private JLayeredPane clockFace; // pane used to store time, clock face, etc
    private Face face;              // Face object (extend JPanel to wrap image) used to store image


    public ClockFace() {
        this.img = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        this.start = new JButton("start");
        this.stop = new JButton("stop");
        this.face = new Face();
        this.time = createTimeLabel("25:00", new Point(100, 125));
        this.status = createStatusLabel("- STOP -", new Point(100, 200));
        this.clockFace = createClockFace();
    }

    public JLayeredPane getClockFace() {
        return clockFace;
    }

    private JLayeredPane createClockFace() {
        JLayeredPane lp = new JLayeredPane();
        this.face.setPreferredSize(new Dimension(300, 300));
        this.face.setBounds(0, 0, 300, 300);

        lp.setPreferredSize(new Dimension(300, 300));
        lp.add(this.face, new Integer(0));
        lp.add(this.time, new Integer(1));
        lp.add(this.status, new Integer(2));
        return lp;
    }


    private JLabel createTimeLabel(String text, Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(false);
        label.setFont(new Font("Arial", Font.PLAIN,40));
        label.setForeground(new Color(32, 173, 107));
        label.setBounds(origin.x, origin.y, 100, 50);
        return label;
    }


    private JLabel createStatusLabel(String text, Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(false);
        label.setFont(new Font("Arial", Font.ITALIC,15));
        label.setForeground(new Color(32, 173, 107));
        label.setBounds(origin.x, origin.y, 100, 25);
        return label;
    }


    // this method is to update time display
    public void setTime(String text) {
        this.time.setText(text);
    }

    // this method is to update status display
    // options: "- RESET -", "- IN SESSION -", "- STOP -"
    public void setStatus(String text) {
        this.time.setText(text);
    }


    // this method is to add new ticks to clock face image
    public void updateTick(int tick) {
        this.face.setTick(tick);
    }

    // this method is to reset ticks of clock face image
    public void resetTick() {
        this.face.resetTick();
    }

    // TODO(1): add listener to start and stop buttons
    // TODO(2): update time per second
    // TODO(3): update ticks very 1/25 of session time
    // TODO(3): update status when (start: "- in session -") (stop: "- stop -") (reset: "- reset -")


    // construct a temporary window to test layout
    public static void main(String args[]) {
        ClockFace cf = new ClockFace();
        JFrame window = new JFrame("ClockFace");
        JPanel content = new JPanel();
        JPanel buttons = new JPanel(new GridLayout(1,2));

        buttons.add(cf.start);
        buttons.add(cf.stop);
        content.setLayout(new BorderLayout());
        content.add(cf.clockFace, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.PAGE_END);

        // examples: test update methods
        cf.updateTick(1);
        cf.updateTick(2);
        cf.updateTick(3);
        cf.setTime("22:00");

        window.setContentPane(content);
        window.setLocation(100,50);
        window.setPreferredSize(new Dimension(300, 320));
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
    }


    private class Face extends JPanel {

        private int time = 0;    // from 0 to 25, indicating 25 ticks at maximum

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = img.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            drawDial(g2d);
            g.drawImage(img,0, 0, 300, 300, null);
        }

        private void drawDial(Graphics2D g2d) {

            int centerX = img.getWidth()/2;
            int centerY = img.getHeight()/2;

            int radius = 200;
            int len = 30;
            int n = 25;

            Color bgColor = new Color(33,33,33);
            Color c1 = new Color(79,79,79);
            Color c2 = new Color(32, 173, 107);

            g2d.setPaint(bgColor);
            g2d.fillRect(0, 0, img.getWidth(), img.getHeight());

            g2d.setColor(c1);
            for(int i = 0; i < n; i++) {
                double t = Math.PI * 198.0 / 180.0 - Math.PI * 9.0 / 180.0 * i;
                int start_x = (int) Math.round(centerX + radius * Math.cos(t));
                int start_y = (int) Math.round(centerY - radius * Math.sin(t));
                int end_x = (int) Math.round(centerX + (radius + len) * Math.cos(t));
                int end_y = (int) Math.round(centerY - (radius + len) * Math.sin(t));

                g2d.setStroke(new BasicStroke(6));
                g2d.drawLine(start_x, start_y, end_x, end_y);
            }


            g2d.setColor(c2);
            for(int j = 0; j < time; j++) {
                double t = Math.PI * 198.0 / 180.0 - Math.PI * 9.0 / 180.0 * j;
                int start_x = (int) Math.round(centerX + radius * Math.cos(t));
                int start_y = (int) Math.round(centerY - radius * Math.sin(t));
                int end_x = (int) Math.round(centerX + (radius + len) * Math.cos(t));
                int end_y = (int) Math.round(centerY - (radius + len) * Math.sin(t));
                g2d.drawLine(start_x, start_y, end_x, end_y);
            }

        }

        // method to add new ticks to clock face image
        public void setTick (int tick) {
            this.time = tick;
            repaint();
        }

        public void resetTick () {
            this.time = 0;
            repaint();
        }

    }
}
