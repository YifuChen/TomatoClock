import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TomatoUI extends JPanel {

    // JTextArea fileList;
    // JButton openButton;
    // JButton saveButton;
    private JLabel title;
    private TomatoTimer t;
    private ClockFace clockface;
    private TimerController sessionController;
    private TimerController breakController;
    private JFileChooser fc;
    private Color textColor;
    private Color bgColor;


    public TomatoUI() {
        super(new GridLayout(1,2));
        //setBorder( new EmptyBorder( 3, 3, 3, 3 ) );


        this.title = new JLabel("Tomato Clock");
        this.clockface = new ClockFace();
        this.fc = new JFileChooser();
        this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.sessionController = new TimerController(this.clockface, "session", 25, true);
        this.breakController = new TimerController(this.clockface, "break", 5, false);

        this.textColor = new Color(218, 218, 219);
        this.bgColor = new Color(33,33,33);

        // UIs

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bgColor);
        titlePanel.setPreferredSize(new Dimension(600, 100));
        this.title.setFont(new Font("Arial", Font.BOLD, 28));
        this.title.setForeground(textColor);
        titlePanel.add(this.title);


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 2));
        controlPanel.setPreferredSize(new Dimension(300, 130));
        controlPanel.add(sessionController.getDefaultPanel());
        controlPanel.add(breakController.getDefaultPanel());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(clockface.getStartButton());
        buttonPanel.add(clockface.getStopButton());


        JPanel todoPanel = new JPanel(new BorderLayout());
        JPanel todoButtons = new JPanel(new GridLayout(1,3));
        TodoTable tb = new TodoTable();
        TodoTable.TodoTableModel model = tb.getModel();
        JButton addBtn = new JButton("add");
        JButton saveBtn = new JButton("save");
        JButton loadBtn = new JButton("load");
        todoButtons.add(addBtn);
        todoButtons.add(saveBtn);
        todoButtons.add(loadBtn);
        todoPanel.add(tb,BorderLayout.CENTER);
        todoPanel.add(todoButtons,BorderLayout.PAGE_END);


        // layout
        JPanel clockUI = new JPanel(new BorderLayout());
        JPanel todoUI = new JPanel(new BorderLayout());

        //clockUI.setPreferredSize(new Dimension(300, 550));

        clockUI.add(this.clockface.getDefaultPanel(), BorderLayout.PAGE_START);
        clockUI.add(controlPanel, BorderLayout.CENTER);
        clockUI.add(buttonPanel, BorderLayout.PAGE_END);

        todoUI.add(todoPanel, BorderLayout.CENTER);

        this.add(clockUI);
        this.add(todoUI);


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

                int returnVal = fc.showSaveDialog(saveBtn);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        PrintWriter writer = new PrintWriter(fc.getSelectedFile(), "UTF-8");

                        for (Task t:model.data){
                            String s = t.isDone()+","+t.getTask()+","+t.getExpected()+","+t.getCompleted();
                            System.out.println(t.isDone());
                            writer.println(s);
                        }


                        writer.close();
                    } catch (Exception ee){
                System.out.println("Problem writing to file: "+ee);
                    }
                }

            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = fc.showSaveDialog(saveBtn);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        Scanner scanner = new Scanner(fc.getSelectedFile());
                        model.clear();
                        while (scanner.hasNext()){
                            String s = scanner.nextLine();
                            List<String> sep = Arrays.asList(s.split(","));
                            model.addTask(new Task(Boolean.parseBoolean(sep.get(0)),
                                    sep.get(1),Integer.parseInt(sep.get(2)),Integer.parseInt(sep.get(3))));
                            }

                        scanner.close();
                    } catch (Exception ee){
                        System.out.println("Problem reading file: "+ee);
                    }
                }

            }
        });






        /**pause.addActionListener(new ActionListener() {
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
                //clockPanel.revalidate();
                //clockPanel.repaint();

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
                    //clockPanel.revalidate();
                    //clockPanel.repaint();
                }
            }
        });**/
    }

}


/**
 *     @Override
 *     public void actionPerformed(ActionEvent e) {
 * //        if (e.getSource() == openButton) {
 * //            int returnVal = fc.showOpenDialog(TomatoUI.this);
 * //            if (returnVal == JFileChooser.APPROVE_OPTION) {
 * //            }
 * //        } else if (e.getSource() == saveButton){
 * //            System.out.println("save");
 * //            BufferedImage res = photoPanel.getImage();
 * //            try {
 * //                if (ImageIO.write(res, "png", new File("./output_image.png")))
 * //                {
 * //                    System.out.println("-- saved");
 * //                }
 * //            } catch (IOException err) {
 * //                err.printStackTrace();
 * //            }
 * //        }
 *     }
 *
 *     public void paintComponent(Graphics g) {
 *         super.paintComponent(g);
 *
 *         // antialising
 *         Graphics2D g2 = (Graphics2D)g;
 *         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 *     }
 * //
 * //    public void addEntry() {
 * //        drawingList.addEntry();
 * //    }
 * //
 * //    public void popDrawing() {
 * //        photoPanel.popDrawing();
 * //    }
 */

