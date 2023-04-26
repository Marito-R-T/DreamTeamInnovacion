package org.example.view;

import org.example.controller.GoogleScholarController;
import org.example.controller.mysql.DatabaseSaveArticleController;
import org.example.controller.mysql.DatabaseSaveAuthorController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principalview extends JFrame {

    public JPanel PrincipalPanel;
    private JButton SearchBtn;
    private JTextField labelInput;
    private JPanel ResultPanel;
    private JTextPane jsonResult;
    private JButton btnSaveAuthors;
    private JSpinner spinnerAuthorSelected;
    private JButton searchArticlesButton;
    private JButton saveArticlesButton;
    private GoogleScholarController scholarController;
    private DatabaseSaveAuthorController dbAuthorController;
    private DatabaseSaveArticleController dbArticleController;

    public Principalview() {
        // Attributes / Controllers
        this.scholarController = new GoogleScholarController();
        this.dbAuthorController = new DatabaseSaveAuthorController();
        this.dbArticleController = new DatabaseSaveArticleController();

        // Interface
        this.spinnerAuthorSelected.setModel(new SpinnerNumberModel(1, 1, 10, 1));

        // actions
        this.SearchBtn.addActionListener(this::actionAuthorPerformedClicked);
        this.btnSaveAuthors.addActionListener(this::saveAuthorsActionPerformedClicked);
        this.searchArticlesButton.addActionListener(this::actionArticlesPerformedClicked);
        this.saveArticlesButton.addActionListener(this::saveArticlesActionPerformedClicked);
    }

    private void saveAuthorsActionPerformedClicked(ActionEvent e) {
        this.dbAuthorController.addAuthors(this.scholarController);
    }

    private void actionAuthorPerformedClicked(ActionEvent e) {
        String response = this.scholarController.searchProfileByLabel(this.labelInput.getText());
        this.jsonResult.setText(response);
    }

    private void saveArticlesActionPerformedClicked(ActionEvent e) {
        this.dbArticleController.addArticles(this.scholarController);
    }

    private void actionArticlesPerformedClicked(ActionEvent e) {
        String response = this.scholarController.searchArticlesByAuthor(
                this.scholarController.getAuthorController().getAuthors().get(
                        (Integer) this.spinnerAuthorSelected.getValue() - 1
                )
        );
        this.jsonResult.setText(response);
    }
}
