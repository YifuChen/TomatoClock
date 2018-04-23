import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TomatoUI extends JPanel implements ActionListener {
    JFileChooser fc;
    JTextArea fileList;
    JButton openButton;
    JButton saveButton;

    public TomatoUI() {
        super(new BorderLayout());
        setBorder( new EmptyBorder( 3, 3, 3, 3 ) );

        // UIs
        JLabel title = new JLabel("<html><h1>Photo Stamper</h1></html>");


        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        openButton = new JButton("open");
        openButton.addActionListener(this);
        saveButton = new JButton("save");
        saveButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);


        // layout
        add(title, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == openButton) {
//            int returnVal = fc.showOpenDialog(TomatoUI.this);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//            }
//        } else if (e.getSource() == saveButton){
//            System.out.println("save");
//            BufferedImage res = photoPanel.getImage();
//            try {
//                if (ImageIO.write(res, "png", new File("./output_image.png")))
//                {
//                    System.out.println("-- saved");
//                }
//            } catch (IOException err) {
//                err.printStackTrace();
//            }
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // antialising
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
//
//    public void addEntry() {
//        drawingList.addEntry();
//    }
//
//    public void popDrawing() {
//        photoPanel.popDrawing();
//    }
}
