package com.openjfx.controllers;

import com.openjfx.models.Income;
import com.openjfx.models.Spend;
import com.openjfx.models.Tag;
import com.openjfx.dao.IncomeDAO;
import com.openjfx.dao.SpendDAO;
import com.openjfx.dao.TagDAO;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.util.Date;

public class MainController {
    // Income elements
    @FXML
    private TableView<Income> tableIncome;
    @FXML
    private TableColumn<Income, Integer> colIdIncome;
    @FXML
    private TableColumn<Income, String> colNameIncome;
    @FXML
    private TableColumn<Income, Double> colMoneyIncome;
    @FXML
    private TableColumn<Income, Date> colDateIncome;
    @FXML
    private TableColumn<Income, Tag> colTagIncome;

    // Spend elements
    @FXML
    private TableView<Spend> tableSpend;
    @FXML
    private TableColumn<Spend, Integer> colIdSpend;
    @FXML
    private TableColumn<Spend, String> colNameSpend;
    @FXML
    private TableColumn<Spend, Double> colMoneySpend;
    @FXML
    private TableColumn<Spend, Date> colDateSpend;
    @FXML
    private TableColumn<Spend, Tag> colTagSpend;
    @FXML
    private TableColumn<Spend, Integer> colQuantitySpend;

    // Tag elements
    @FXML
    private TableView<Tag> tableTag;
    @FXML
    private TableColumn<Tag, Integer> colIdTag;
    @FXML
    private TableColumn<Tag, String> colNameTag;
    @FXML
    private TableColumn<Tag, String> colColorTag;

    // Botones
    @FXML
    private Button btnAddSpend;
    @FXML
    private Button btnAddIncome;
    @FXML
    private Button btnAddTag;

    // Lists
    private ObservableList<Income> listIncomes;
    private ObservableList<Spend> listSpends;
    private ObservableList<Tag> listTags;

    // Initialize
    @FXML
    public void initialize() {
        // Income columns
        colIdIncome.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameIncome.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMoneyIncome.setCellValueFactory(new PropertyValueFactory<>("money"));
        colDateIncome.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTagIncome.setCellValueFactory(new PropertyValueFactory<>("tag"));

        colTagIncome.setCellValueFactory(new PropertyValueFactory<>("tag"));
        colTagIncome.setCellFactory(new Callback<TableColumn<Income, Tag>, TableCell<Income, Tag>>() {
            @Override
            public TableCell<Income, Tag> call(TableColumn<Income, Tag> param) {
                return new TableCell<Income, Tag>() {
                    @Override
                    protected void updateItem(Tag tag, boolean empty) {
                        super.updateItem(tag, empty);
                        if (empty || tag == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(tag.getName());
                            setStyle("-fx-background-color: " + tag.getColor() + ";"); // Establece el color de fondo
                        }
                    }
                };
            }
        });

        // Spend columns
        colIdSpend.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNameSpend.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMoneySpend.setCellValueFactory(new PropertyValueFactory<>("money"));
        colDateSpend.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTagSpend.setCellValueFactory(new PropertyValueFactory<>("tag"));
        colQuantitySpend.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        colTagSpend.setCellValueFactory(new PropertyValueFactory<>("tag"));
        colTagSpend.setCellFactory(new Callback<TableColumn<Spend, Tag>, TableCell<Spend, Tag>>() {
            @Override
            public TableCell<Spend, Tag> call(TableColumn<Spend, Tag> param) {
                return new TableCell<Spend, Tag>() {
                    @Override
                    protected void updateItem(Tag tag, boolean empty) {
                        super.updateItem(tag, empty);
                        if (empty || tag == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(tag.getName());
                            setStyle("-fx-background-color: " + tag.getColor() + ";"); // Establece el color de fondo
                        }
                    }
                };
            }
        });

        // Load
        loadData();
    }

    // Load from Database
    private void loadData() {
        // Get Incomes
        listIncomes = FXCollections.observableArrayList(IncomeDAO.getAllIncomes());
        tableIncome.setItems(listIncomes);

        // Get Spends
        listSpends = FXCollections.observableArrayList(SpendDAO.getAllSpends());
        tableSpend.setItems(listSpends);
    }

    // Add Spend
    @FXML
    private void onAddSpend(ActionEvent event) {
        // Lógica para abrir la ventana de añadir Spend
        System.out.println("Añadir Spend");
    }

    // Add Income
    @FXML
    private void onAddIncome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/openjfx/AddIncome.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Añadir Ingreso");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Tag
    @FXML
    private void onAddTag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/openjfx/AddTag.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Añadir Etiqueta");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRefreshData(ActionEvent event) {
        loadData();
        System.out.println("Datos refrescados");
    }
}
