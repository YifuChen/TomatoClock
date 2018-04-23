import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Vector;

public class TodoList extends JPanel {
    private JList<CheckableItem> list;
    Vector<CheckableItem> items;


    public TodoList() {
        items = new Vector<CheckableItem>();
        items.add(new CheckableItem("default task"));

        this.list = new JList(items);
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(10);
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                CheckableItem item = (CheckableItem) list.getModel()
                        .getElementAt(index);
                item.setSelected(!item.isSelected());
                Rectangle rect = list.getCellBounds(index, index);
                list.repaint(rect);
            }
        });
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(300, 300));

        this.add(listScrollPane);
    }

    public boolean addTask(String task) {
        items.add(new CheckableItem(task));
        Rectangle rect = list.getCellBounds(items.size() - 1, items.size() - 1);
        list.repaint(rect);

        return true;
    }

    class CheckListRenderer extends JCheckBox implements ListCellRenderer {

        public CheckListRenderer() {
            setBackground(UIManager.getColor("List.textBackground"));
            setForeground(UIManager.getColor("List.textForeground"));
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus) {
            setEnabled(list.isEnabled());
            setSelected(((CheckableItem) value).isSelected());
            setFont(list.getFont());
            setText(value.toString());
            return this;
        }
    }

    class CheckableItem {
        private String str;

        private boolean isSelected;

        public CheckableItem(String str) {
            this.str = str;
            isSelected = false;
        }

        public void setSelected(boolean b) {
            isSelected = b;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public String toString() {
            return str;
        }
    }
}
