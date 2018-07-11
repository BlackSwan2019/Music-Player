import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserInterface extends JFrame {
    private JPanel listPanel = new JPanel();
    private JPanel controls = new JPanel();

    private JButton play = new JButton("Play");
    private JButton stop = new JButton("Stop");

    private JTable list;

    private JScrollPane scroller = new JScrollPane();

    UserInterface() {
        setTitle("Music Player");
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create table of songs.
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
        scroller.setBackground(Color.BLACK);
        scroller.setViewportView(list);
        scroller.getViewport().setBackground(Color.BLACK);
        scroller.setBorder(BorderFactory.createRaisedBevelBorder());
        listPanel.add(scroller, BorderLayout.CENTER);

        controls.setBackground(Color.BLACK);
        play.addActionListener(new ButtonListener());
        controls.add(play);
        stop.addActionListener(new ButtonListener());
        controls.add(stop);
        add(controls, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        HeaderRenderer(JTable table) {
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

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Play")) {
                System.out.println("Play button pressed.");
            } else if (e.getActionCommand().equalsIgnoreCase("Stop")) {
                System.out.println("Stop button pressed.");
            }
        }
    }
}
