import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationSystem extends JFrame {
    private List<String> reservations;
    private JTextField nameField;
    private JButton reserveButton;
    private JButton cancelButton;

    public ReservationSystem() {
        reservations = new ArrayList<>();

        setTitle("Reservation System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    reservations.add(name);
                    JOptionPane.showMessageDialog(null, "Reservation successful for " + name);
                    nameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CancellationForm(reservations);
            }
        });

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        reserveButton = new JButton("Reserve");
        reserveButton.setBounds(10, 50, 120, 25);
        panel.add(reserveButton);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(140, 50, 160, 25);
        panel.add(cancelButton);
    }
}
