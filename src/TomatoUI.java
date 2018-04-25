import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TomatoUI extends JPanel implements ActionListener {
    JFileChooser fc;
//    JTextArea fileList;
//    JButton openButton;
//    JButton saveButton;
    TomatoTimer t;

    public TomatoUI() {
        super(new BorderLayout());
        setBorder( new EmptyBorder( 3, 3, 3, 3 ) );

        // UIs
        JLabel title = new JLabel("<html><h1>Tomato</h1></html>");


        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        TodoTable tb = new TodoTable();
        ClockFace cf = new ClockFace();
        TodoTable.TodoTableModel model = tb.getModel();


        JButton start = new JButton("start");
        JButton pause = new JButton("pause");
        JButton stop = new JButton("stop");
        JPanel clockButtons = new JPanel(new GridLayout(1,2));
        clockButtons.add(start);
        JPanel clockPanel = new JPanel(new BorderLayout());
        clockPanel.add(cf.getClockFace(), BorderLayout.CENTER);
        clockPanel.add(clockButtons, BorderLayout.PAGE_END);

        JButton addBtn = new JButton("add");
        JButton saveBtn = new JButton("save");
        JButton loadBtn = new JButton("load");


        JPanel todoPanel = new JPanel(new BorderLayout());
        JPanel todoButtons = new JPanel(new GridLayout(1,3));
        todoButtons.add(addBtn);
        todoButtons.add(saveBtn);
        todoButtons.add(loadBtn);
        todoPanel.add(tb,BorderLayout.CENTER);
        todoPanel.add(todoButtons,BorderLayout.PAGE_END);


        // layout
        add(title, BorderLayout.PAGE_START);
        add(clockPanel, BorderLayout.CENTER);
        add(todoPanel, BorderLayout.LINE_END);

        // example of adding a task
        tb.getModel().addTask("test");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog("Please input the name");
                model.addTask(s);
            }
        });




        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String s = todo.toString();
//                int returnVal = fc.showSaveDialog(saveBtn);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    try {
//                    PrintWriter writer = new PrintWriter(fc.getSelectedFile(), "UTF-8");
//                writer.println(s);
//                writer.close();
//                    } catch (Exception ee){
//                System.out.println("Problem writing to file: "+ee);
//                    }
//                }

            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = fc.showSaveDialog(saveBtn);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        Scanner scanner = new Scanner(fc.getSelectedFile());
                        String s = scanner.nextLine();
                        model.clear();
                        int i = 0;
                        while (i < s.length()){
                            int j = i;
                            while (j < s.length() && s.charAt(j)!= ',') j++;
                            String temp = s.substring(i,j);
                            System.out.println(temp);
                            model.addTask(temp);
                            i = j+1;
                        }
                        scanner.close();
                    } catch (Exception ee){
                        System.out.println("Problem reading file: "+ee);
                    }
                }

            }
        });






        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isPaused = t.togglePause();
                if (isPaused) {
                    pause.setText("resume");
                } else {
                    pause.setText("pause");
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.destroy();
                t = null;
                clockButtons.remove(pause);
                clockButtons.remove(stop);
                clockButtons.add(start);
                clockPanel.revalidate();
                clockPanel.repaint();

                // TODO: replace this with time from setting
                cf.setTime("25:00");
                cf.resetTick();
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t == null) {
                    t = new TomatoTimer(25, cf);
                    clockButtons.remove(start);
                    clockButtons.add(pause);
                    clockButtons.add(stop);
                    clockPanel.revalidate();
                    clockPanel.repaint();
                }
            }
        });
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
