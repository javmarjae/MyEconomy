package com.openjfx.controllers;

import com.openjfx.models.Database;
import com.openjfx.dao.TagDAO;
import com.openjfx.models.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

public class AddTagController {
    // Elements
    @FXML
    private TextField nameField;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    public void handleAddTag() {
        String name = nameField.getText();
        Color selectedColor = colorPicker.getValue();
        String color = String.format("#%02X%02X%02X",
                (int) (selectedColor.getRed() * 255),
                (int) (selectedColor.getGreen() * 255),
                (int) (selectedColor.getBlue() * 255));

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn
                        .prepareStatement("INSERT INTO tags (name, color) VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, color);

            pstmt.executeUpdate();
            System.out.println("Etiqueta agregada exitosamente");

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
