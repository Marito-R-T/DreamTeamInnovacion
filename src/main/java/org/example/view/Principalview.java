package org.example.view;

import org.example.controller.GoogleScholarController;
import org.example.controller.mysql.DatabaseSaveAuthorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principalview extends JFrame {

    public JPanel PrincipalPanel;
    private JButton SearchBtn;
    private JTextField labelInput;
    private JPanel ResultPanel;
    private JTextPane jsonResult;
    private JButton btnSaveAuthors;
    private GoogleScholarController scholarController;
    private DatabaseSaveAuthorController dbAuthorController;

    public Principalview() {
        this.scholarController = new GoogleScholarController();
        this.dbAuthorController = new DatabaseSaveAuthorController();
        this.SearchBtn.addActionListener(this::actionPerformedClicked);
        this.btnSaveAuthors.addActionListener(this::saveAuthorsActionPerformedClicked);
    }

    private void saveAuthorsActionPerformedClicked(ActionEvent e) {
        this.dbAuthorController.addAuthors(this.scholarController);
    }

    private void actionPerformedClicked(ActionEvent e) {
        String response = this.scholarController.searchProfileByLabel(this.labelInput.getText());
        this.jsonResult.setText(response);
    }
}
