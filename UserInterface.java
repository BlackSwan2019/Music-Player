import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class UserInterface extends JFrame {
    JPanel listPanel = new JPanel();
    JPanel controls = new JPanel();

    JButton play = new JButton("Play");
    JButton stop = new JButton("Stop");

    JTable list;

    JScrollPane scrollPane = new JScrollPane();

    UserInterface() {
        setTitle("Music Player");
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        // Populate track table.
        MusicCollector music = new MusicCollector();
        String[] columnNames = {"Song Name"};
        Object[][] tracks = music.collectMusic();
        list = new JTable(tracks, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        list.getTableHeader().setUI(null);
        JTableHeader header = new JTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(list));
        list.setBackground(Color.BLACK);
        list.setForeground(Color.WHITE);
        list.setRowHeight(45);
        list.setShowGrid(false);

        listPanel.setBackground(Color.BLACK);
        listPanel.setLayout(new BorderLayout());

        add(listPanel, BorderLayout.CENTER);

        // Add scrollable music list to list panel.
        JScrollPane scroller = new JScrollPane();
        scroller.setBackground(Color.BLACK);
        scroller.setViewportView(list);
        scroller.getViewport().setBackground(Color.BLACK);
        scroller.setBorder(BorderFactory.createRaisedBevelBorder());
        listPanel.add(scroller, BorderLayout.CENTER);

        add(controls, BorderLayout.PAGE_END);
        controls.setBackground(Color.BLACK);
        controls.add(play);
        controls.add(stop);

        setVisible(true);
    }

    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer)
                    table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.LEFT);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }
    }
}
