package com.openjfx.controllers;

import com.openjfx.models.Database;
import com.openjfx.dao.TagDAO;
import com.openjfx.models.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

public class AddIncomeController {
    // Elements
    @FXML
    private TextField nameField;
    @FXML
    private TextField moneyField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> tagComboBox;

    @FXML
    public void initialize() {
        loadTags();
    }

    private void loadTags() {
        List<Tag> tags = TagDAO.getAllTags();
        for (Tag tag : tags) {
            tagComboBox.getItems().add(tag.getName());
        }
    }

    @FXML
    public void handleAddIncome() {
        String name = nameField.getText();
        String money = moneyField.getText();
        LocalDate date = dateField.getValue();
        String tag = tagComboBox.getValue();

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn
                        .prepareStatement("INSERT INTO incomes (name, money, date, tag_id) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, Double.parseDouble(money));
            pstmt.setDate(3, java.sql.Date.valueOf(date));
            pstmt.setInt(4, TagDAO.getTagIdByName(tag));

            pstmt.executeUpdate();
            System.out.println("Ingreso agregado exitosamente");

            // Cierra la ventana o actualiza la vista según sea necesario
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
