import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CancellationForm extends JFrame {
    private List<String> reservations;
    private JComboBox<String> reservationList;
    private JButton cancelButton;

    public CancellationForm(List<String> reservations) {
        this.reservations = reservations;

        setTitle("Cancellation Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel listLabel = new JLabel("Reservations:");
        listLabel.setBounds(10, 20, 100, 25);
        panel.add(listLabel);

        reservationList = new JComboBox<>(reservations.toArray(new String[0]));
        reservationList.setBounds(120, 20, 150, 25);
        panel.add(reservationList);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(90, 60, 120, 25);
        panel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) reservationList.getSelectedItem();
                if (selected != null) {
                    reservations.remove(selected);
                    reservationList.removeItem(selected);
                    JOptionPane.showMessageDialog(null, "Reservation cancelled for " + selected);
                }
            }
        });
    }
}
