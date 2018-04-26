import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JPanel {

    private JLabel name;
    private boolean mouseEntered;
    private boolean mouseClicked;

    public MyButton(String name, int width, int height) {
        this.name = new JLabel(name);
        this.mouseEntered = false;
        this.mouseClicked = false;

        this.setPreferredSize(new Dimension(width, height));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.add(this.name, gbc);

        this.setFont("Arial", 20, Color.WHITE);
        addListener();
    }

    public void setFont(String name, int size, Color c) {
        this.name.setFont(new Font(name, Font.PLAIN, size));
        this.name.setForeground(c);
    }

    public void setSize(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

    private void addListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyButton b = (MyButton) e.getSource();
                b.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MyButton b = (MyButton) e.getSource();
                b.mouseEntered = true;
                b.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MyButton b = (MyButton) e.getSource();
                b.mouseEntered = false;
                b.repaint();
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(184, 208, 135);
        Color color2 = new Color(0, 153, 109);
        GradientPaint gp;
        if (this.mouseEntered) {
            gp = new GradientPaint(0, 0, color2, w, 0, color2);
        } else {
            gp = new GradientPaint(0, 0, color1, w, 0, color2);
        }
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyButton panel = new MyButton("test", 200, 40);
        frame.add(panel);
        frame.setSize(200, 200);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
