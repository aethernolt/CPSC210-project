package ui;

import java.awt.event.*;

public class ButtonClickListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("OK")) {
            statusLabel.setText("Ok Button clicked.");
        } else if (command.equals("Submit")) {
            statusLabel.setText("Submit Button clicked.");
        } else {
            statusLabel.setText("Cancel Button clicked.");
        }
    }
}
