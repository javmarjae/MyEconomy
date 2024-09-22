package com.openjfx.controllers;

import com.openjfx.models.Spend;
import com.openjfx.models.Income;
import com.openjfx.models.Tag;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SpendController {

    @FXML
    private ListView<Spend> listSpends;

    @FXML
    private Label spendsDetails;

    @FXML
    private ComboBox<String> tagCombo;

    @FXML
    private TextField newTag;

    @FXML
    private Button buttonTag;

    @FXML
    private Spend selectedSpend;

}
