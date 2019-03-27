package pl;

import bll.AdminBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import bll.model.StudentProfile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    //
    AlertBox ab;
    Stage window;
    private StudentProfile student;
    private AdminBLL adminBLL;
    //crud on student
    private TableView<StudentProfile> students;
    private TextField identifNum;
    private TextField firstName;
    private TextField lastName;
    private TextField group;
    private TextField average;
    private TextField scholarShup;
    private ProgressBar fullInfo;

    //generate reports
    private TextField searchField;
    private Label lastNameL;
    private Label firstNameL;
    private Label groupL;
    private DatePicker startDate;
    private DatePicker endDate;
    private ListView<String> activities;

    public AdminController(){
        adminBLL = new AdminBLL();
        window = new Stage();
        window.setTitle("Administrator Profile");
        window.setOnCloseRequest(e->handleWindowClosure());

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
        viewStudents();
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
        Button createStudentButton = new Button("Create");
        createStudentButton.setOnAction(e->handleCreateStudent());
        Button updateStudentButton = new Button("Update");
        updateStudentButton.setOnAction(e->handleUpdateStudent());
        Button deleteStudentButton = new Button("Delete");
        deleteStudentButton.setOnAction(e->handleDeleteStudent());
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
        startDate = new DatePicker();startDate.setValue(LocalDate.now());
        endDate = new DatePicker();endDate.setValue(LocalDate.now());
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e->handleStudentSearch());
        Button filterButton = new Button("Filter");
        filterButton.setOnAction(e->handleFilterAction());
        activities = new ListView<>();

        List<Label> labels =  new ArrayList<>();
        labels.add(firstNameL);
        labels.add(lastNameL);
        labels.add(groupL);

        AdminTabCreator.createRepGen(tab,searchField,labels,startDate,endDate,searchButton,filterButton,activities);
        ObservableList<String> ac = adminBLL.viewActivities(null);
        activities.setItems(ac);
    }

    /**create/view/update/delete student profiles*/
    private void handleCreateStudent(){
        String idStudentText = identifNum.getText();
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String groupText = group.getText();
        String averageText = average.getText();
        String scholarShipText = scholarShup.getText();

        String message = adminBLL.createStudent(idStudentText,firstNameText,lastNameText,groupText,averageText,scholarShipText);
        if(message != null) ab = new AlertBox(message);
        else {cleanFields();viewStudents();}
    }
    private void viewStudents(){
        ObservableList<StudentProfile> sps = adminBLL.viewStudents();
        students.setItems(sps);
    }
    private void handleUpdateStudent(){
        String groupText = group.getText();
        String averageText = average.getText();
        String scholarShipText = scholarShup.getText();

        StudentProfile oldStudent = students.getSelectionModel().getSelectedItem();
        if(oldStudent == null) return;

        String message = adminBLL.updateStudent(oldStudent,groupText,averageText,scholarShipText);
        if(message != null) ab = new AlertBox(message);
        else {cleanFields();viewStudents();}
    }
    private void handleDeleteStudent(){
        StudentProfile oldStudent = students.getSelectionModel().getSelectedItem();
        if(oldStudent == null) return;
        String message = adminBLL.deleteStudent(oldStudent);
        if(message != null) ab = new AlertBox(message);
        else {cleanFields();viewStudents();}
    }
    private void cleanFields(){
        identifNum.clear();
        firstName.clear();
        lastName.clear();
        group.clear();
        average.clear();
        scholarShup.clear();
    }

    /**generate reports based on student's activity for a time period*/
    private void handleStudentSearch(){
        String studentID = searchField.getText();
        searchField.clear();
        student = adminBLL.findStudent(studentID);
        if(student == null){
            lastNameL.setText("No data available");
            firstNameL.setText("No data available");
            groupL.setText("No data available");
            ObservableList<String> ac = FXCollections.observableArrayList();
            activities.setItems(ac);
            return;
        }
        lastNameL.setText(student.getLastName());
        firstNameL.setText(student.getFirstName());
        groupL.setText(student.getGroup());

        ObservableList<String> ac = adminBLL.viewActivities(student);
        activities.setItems(ac);
    }
    private void handleFilterAction(){
        LocalDate dateStart = startDate.getValue();
        LocalDate dateEnd = endDate.getValue();
        ObservableList<String> activ = adminBLL.filterActivities(student,dateStart,dateEnd);
        activities.setItems(activ);
    }

    private void handleWindowClosure(){
        window.close();
        new LoginController();
    }

}
