package pl.controller;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    //crud on student
    private TableView<Object> students;
    private TextField identifNum;
    private TextField firstName;
    private TextField lastName;
    private TextField group;
    private TextField average;
    private TextField scholarShup;

    private ProgressBar fullInfo;
    private Button viewProfileButton;
    private Button createStudentButton;
    private Button updateStudentButton;
    private Button deleteStudentButton;

    //generate reports
    private TextField searchField;
    private Label lastNameL;
    private Label firstNameL;
    private Label groupL;
    private DatePicker startDate;
    private DatePicker endDate;
    private Button searchButton;
    private Button filterButton;
    private ListView<String> activities;
    public AdminController(){
        Stage window = new Stage();
        window.setTitle("Administrator Profile");

        TabPane layout = new TabPane();
        Tab tab1 = new Tab("CRUD student");
        tab1.setClosable(false);
        initiateCRUDerTab(tab1);
        Tab tab2 = new Tab("Generate reports");
        tab2.setClosable(false);
        initiateRepGenTab(tab2);
        layout.getTabs().addAll(tab1,tab2);

        Scene scene = new Scene(layout,1200,800);
        scene.getStylesheets().add("DarkTheme.css");
        window.setScene(scene);
        window.show();
    }

    private void initiateCRUDerTab(Tab tab){
        List<TextField> textFieldList =  new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        students = new TableView<>();
        identifNum = new TextField();
        firstName = new TextField();
        lastName = new TextField();
        group = new TextField();
        average = new TextField();
        scholarShup = new TextField();
        textFieldList.add(identifNum);
        textFieldList.add(firstName);
        textFieldList.add(lastName);
        textFieldList.add(group);
        textFieldList.add(average);
        textFieldList.add(scholarShup);

        fullInfo = new ProgressBar();
        viewProfileButton = new Button("View Profile");
        viewProfileButton.setOnAction(e->handleViewProfile());
        createStudentButton = new Button("Create");
        createStudentButton.setOnAction(e->handleCreateStudent());
        updateStudentButton = new Button("Update");
        updateStudentButton.setOnAction(e->handleUpdateStudent());
        deleteStudentButton = new Button("Delete");
        deleteStudentButton.setOnAction(e->handleDeleteStudent());
        buttons.add(viewProfileButton);
        buttons.add(createStudentButton);
        buttons.add(updateStudentButton);
        buttons.add(deleteStudentButton);

        AdminTabCreator.createCRUDer(tab,textFieldList,buttons,students,fullInfo);
    }

    private void initiateRepGenTab(Tab tab){
        searchField = new TextField();
        lastNameL = new Label("info");
        firstNameL = new Label("info");
        groupL = new Label("info");
        startDate = new DatePicker();
        endDate = new DatePicker();
        searchButton = new Button("Search");
        filterButton = new Button("Filter");
        activities = new ListView<>();

        List<Label> labels =  new ArrayList<>();
        labels.add(firstNameL);
        labels.add(lastNameL);
        labels.add(groupL);

        AdminTabCreator.createRepGen(tab,searchField,labels,startDate,endDate,searchButton,filterButton,activities);
    }

    private void handleViewProfile(){

    }

    private void handleCreateStudent(){}

    private void handleUpdateStudent(){}

    private void handleDeleteStudent(){}
}
