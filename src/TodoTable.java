import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.HashMap;

public class TodoTable extends JPanel {

    public TodoTable() {
        super(new GridLayout(2, 0));
        TodoTableModel model = new TodoTableModel();
        final JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.setPreferredSize(new Dimension(300, 300));
        table.setGridColor(Color.LIGHT_GRAY);
        add(table);
    }

    class TodoTableModel extends AbstractTableModel {
        private String[] columnNames = new String[]{
                "done",
                "task",
                "expected",
                "completed"
        };
        private HashMap<Integer, Object[]> data;

        public TodoTableModel() {
            data = new HashMap<>();
            data.put(0, new Object[]{new Boolean(true),"do hw", new Integer(5), new Integer( 4)});
            data.put(1, new Object[]{new Boolean(true),"do hw", new Integer(5), new Integer( 4)});
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
            System.out.println(rowIndex + " " + columnIndex);
            Object[] task = data.get(rowIndex);
            return task[columnIndex];
        }

        public void setValueAt(Object value, int row, int col) {
            Object[] task = data.get(row);
            task[col] = value;
            fireTableCellUpdated(row, col);
        }

        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TodoTable newContentPane = new TodoTable();
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
