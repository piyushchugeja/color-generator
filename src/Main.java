import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.Vector;

public class Main extends JFrame {
    JButton generate, previous;
    ImageIcon icon;
    JLabel colorCode;
    JPanel panel;
    GridBagLayout layout;
    private Boolean copied = false;
    private String color = "#";
    private Vector <String> colors = new Vector<String>();
    private int index = 0;
    public Main() {
        super("Random color generator");
        icon = new ImageIcon("src\\Images\\icon.png");
        setIconImage(icon.getImage());
        makeGUI();
        generateColor();
        setColor(color);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        setSize(350, 350);
    }
    private void makeGUI() {
        // reduce tooltip's delay
        ToolTipManager.sharedInstance().setInitialDelay(50);

        // initialise objects
        generate = new JButton("New color");
        previous = new JButton("Previous");
        colorCode = new JLabel("color Code");
        layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel = new JPanel(layout);
        setLayout(new BorderLayout());

        // edit buttons and add action listener
        generate.setPreferredSize(new Dimension(120, 30));
        generate.setFont(new Font("Arial", Font.PLAIN, 17));
        generate.setForeground(Color.decode("#001100"));
        generate.setBackground(Color.decode("#F2F2F2"));
        generate.setBorder(BorderFactory.createLineBorder(Color.decode("#414b3b"), 1, true));
        generate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generate.addActionListener(e -> {
            generateColor();
            setColor(color);
            index = colors.size() - 1;
        });

        previous.setPreferredSize(new Dimension(120, 30));
        previous.setFont(new Font("Arial", Font.PLAIN, 17));
        previous.setForeground(Color.decode("#001100"));
        previous.setBackground(Color.decode("#F2F2F2"));
        previous.setBorder(BorderFactory.createLineBorder(Color.decode("#414b3b"), 1, true));
        previous.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previous.addActionListener(e -> {
            if (index > 0) {
                index--;
                setColor(colors.get(index));
            } else JOptionPane.showMessageDialog(null, "You have reached the last color.", "Error", JOptionPane.ERROR_MESSAGE);
        });

        // edit label and add action listener
        colorCode.setFont(new Font("Arial", Font.PLAIN, 18));
        colorCode.setToolTipText("Click to copy");
        colorCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StringSelection stringSelection = new StringSelection(colorCode.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                copied = true;
                colorCode.setText("Copied!");
                colorCode.setToolTipText(color);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (copied) colorCode.setText("Copied!");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (copied) colorCode.setText(color);
            }
        });

        // add objects to panel
        gbc.gridx = 1;
        panel.add(colorCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(previous, gbc);
        gbc.gridx = 2;
        panel.add(generate, gbc);

        // add panel to frame
        panel.setSize(350, 350);
        add(panel);
    }
    private void setColor(String color) {
        colorCode.setText(color);
        panel.setBackground(Color.decode(color));
        colorCode.setForeground(ColorHelper.contrastColorByShift(Color.decode(color), 156));
        colorCode.setToolTipText("Click to copy");
        copied = false;
        colors.add(color);
        previous.setEnabled(colors.size() >= 2);
    }
    private void generateColor() {
        color = "#";
        for (int i = 0; i < 6; i++) {
            String characters = "0123456789ABCDEF";
            color += characters.charAt((int) (Math.random() * 16));
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
