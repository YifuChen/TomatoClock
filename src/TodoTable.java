import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TodoTable extends JPanel {

    private TodoTableModel model;
    private Color textColor = new Color(218, 218, 219);
    private Color bgColor = new Color(40,40,40);
    private Color accentColor = new Color(0, 153, 109);
    private Color selectedColor = new Color(50,50,50);

    public TodoTable() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.model = new TodoTableModel();

        final JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        scrollPane.setPreferredSize(new Dimension(300, 300));

        table.setBackground(bgColor);
        table.setGridColor(bgColor);
        table.setForeground(textColor);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setSelectionBackground(selectedColor);
        table.setBorder(BorderFactory.createEmptyBorder());


        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(300, 30));
        header.setFont(new Font("Arial", Font.ITALIC, 16));
        header.setForeground(textColor);
        header.setBackground(accentColor);


        final TableCellRenderer tcrOs = table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                lbl.setForeground(textColor);
                lbl.setBackground(accentColor);
                lbl.setBorder(BorderFactory.createEmptyBorder());
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                return lbl;
            }
        });

        scrollPane.getViewport().setBackground(bgColor);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0,0,0,0, new Color(79,79,79)));

        this.add(scrollPane);
    }

    public TodoTableModel getModel() {
        return model;
    }

    class TodoTableModel extends AbstractTableModel {
        private String[] columnNames = new String[]{
                " ",
                "Task",
                "Total",
                "Now"
        };
        public ArrayList<Task> data;
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
            fireTableRowsInserted(row, row);
            return row;
        }

        public int addTask(Task task) {
            data.add(task);
            int row = data.size() - 1;
            indexLookup.put(task.getTask(), row);
            fireTableRowsInserted(row, row);
            return row;
        }

        public void clear() {
            data = new ArrayList<>();
            indexLookup = new HashMap<>();
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
