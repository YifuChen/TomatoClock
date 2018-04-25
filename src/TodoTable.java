import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoTable extends JPanel {
    private TodoTableModel model;

    public TodoTableModel getModel() {
        return model;
    }

    public TodoTable() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new TodoTableModel();
        final JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        table.setGridColor(Color.LIGHT_GRAY);
        add(scrollPane);
        add(new JLabel("placeholder for stats"));
    }

    class TodoTableModel extends AbstractTableModel {
        private String[] columnNames = new String[]{
                "done",
                "task",
                "expected",
                "completed"
        };
        private ArrayList<Task> data;
        private HashMap<String, Integer> indexLookup;

        public TodoTableModel() {
            data = new ArrayList<Task>();
            indexLookup = new HashMap<>();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public int addTask(String task) {
            data.add(new Task(false, task, 0, 0));
            int row = data.size() - 1;
            indexLookup.put(task, row);
            return row;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Task t = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (boolean) t.isDone();
                case 1:
                    return t.getTask();
                case 2:
                    return t.getExpected();
                case 3:
                    return t.getCompleted();
            }
            return null;
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Task t = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    t.setDone((boolean) value);
                    break;
                case 1:
                    t.setTask((String) value);
                    break;
                case 2:
                    t.setExpected((int) value);
                    break;
                case 3:
                    t.setCompleted((int) value);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public int getTaskRow(String task) {
            if (indexLookup.containsKey(task)) {
                return indexLookup.get(task);
            }
            System.out.println("task not found, creating a new task");
            int row = addTask(task);
            return row;
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TodoTable newContentPane = new TodoTable();

        // exmaple of adding a task
        newContentPane.getModel().addTask("do hw");

        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
