package org.example.view;

import org.example.controller.GoogleScholarController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Principalview extends JFrame {

    public JPanel PrincipalPanel;
    private JButton SearchBtn;
    private JTextField labelInput;
    private JPanel ResultPanel;
    private JTextPane jsonResult;
    private GoogleScholarController scholarController;

    public Principalview() {
        scholarController = new GoogleScholarController();
        SearchBtn.addActionListener(e -> {
            this.actionPerformedClicked(e);
        });
    }

    private void actionPerformedClicked(ActionEvent e) {
        String response = this.scholarController.searchProfileByLabel(this.labelInput.getText());
        this.jsonResult.setText(response);
    }
}
