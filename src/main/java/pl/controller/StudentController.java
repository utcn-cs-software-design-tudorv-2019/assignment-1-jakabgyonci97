package pl.controller;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    //personal information
    private TextField firstName; private Label firstNameLabel;
    private TextField lastName; private Label lastNameLabel;
    private TextField icn; private Label icnLabel;
    private TextField pnc; private Label pncLabel;
    private Button createPersonal;
    private Button updatePersonal;

    //student information
    private TextField idStudent; private Label idStudentLabel;
    private TextField group; private Label groupLabel;
    private TextField scholarShip; private Label scholarShipLabel;
    private TextField grades; private Label gradesLabel;
    private Button createStudent;
    private Button updateStudent;
    private Button deleteStudent;

    //contact information
    private TextField address; private Label addressLabel;
    private TextField phoneNumber; private Label phoneNUmberLabel;
    private TextField emailAddress; private Label emailAddressLabel;
    private Button createContact;
    private Button updateContact;
    private Button deleteContact;

    //enrollment information
    private ListView<String> courses;
    private Label courseName;
    private Label courseSession;
    private Label courseExamDate;
    private TextField searchField;
    private TextField keyField;
    private Button searchButton;
    private Button enrollButton;

    public StudentController(){
        Stage window = new Stage();
        window.setTitle("Student Profile");

        TabPane layout = new TabPane();
        Tab tab1 = new Tab("Personal Info");
        tab1.setClosable(false);
        initiatePersonalInfoTab(tab1);
        Tab tab2 = new Tab("Student Info");
        tab2.setClosable(false);
        initiateStudentInfoTab(tab2);
        Tab tab3 = new Tab("Contact Info");
        tab3.setClosable(false);
        initiateContactInfoTab(tab3);
        Tab tab4 = new Tab("Courses");
        tab4.setClosable(false);
        initiateEnrollmentTab(tab4);
        layout.getTabs().addAll(tab1,tab2,tab3,tab4);

        Scene scene = new Scene(layout,1200,800);
        scene.getStylesheets().add("DarkTheme.css");
        window.setScene(scene);
        window.show();
    }

    private void initiatePersonalInfoTab(Tab personalInfo){
        List<TextField> textFields = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();

        firstName = new TextField();textFields.add(firstName);
        lastName = new TextField();textFields.add(lastName);
        icn =  new TextField();textFields.add(icn);
        pnc = new TextField();textFields.add(pnc);

        firstNameLabel = new Label();labels.add(firstNameLabel);
        lastNameLabel = new Label();labels.add(lastNameLabel);
        icnLabel = new Label();labels.add(icnLabel);
        pncLabel =  new Label();labels.add(pncLabel);

        createPersonal = new Button("Create");buttons.add(createPersonal);
        createPersonal.setOnAction(e->handlePersonalInfoCreation());
        updatePersonal = new Button("Update");buttons.add(updatePersonal);
        updatePersonal.setOnAction(e->handlePersonalInfoUpdate());

        StudentTabCreator.createPersonal(personalInfo,textFields,labels,buttons);
    }

    private void initiateStudentInfoTab(Tab studentInfo){
        List<TextField> textFields = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();

        idStudent = new TextField(); textFields.add(idStudent);
        group = new TextField(); textFields.add(group);
        scholarShip = new TextField(); textFields.add(scholarShip);
        grades = new TextField(); textFields.add(grades);

        idStudentLabel = new Label();labels.add(idStudentLabel);
        groupLabel = new Label();labels.add(groupLabel);
        scholarShipLabel = new Label();labels.add(scholarShipLabel);
        gradesLabel = new Label();labels.add(gradesLabel);

        createStudent = new Button("Create"); buttons.add(createStudent);
        createStudent.setOnAction(e->handleStudentInfoCreation());
        updateStudent = new Button("Update"); buttons.add(updateStudent);
        updateStudent.setOnAction(e->handleStudentInfoUpdate());
        deleteStudent = new Button("Delete"); buttons.add(deleteStudent);
        deleteStudent.setOnAction(e->handleStudentInfoDelete());

        StudentTabCreator.createStudent(studentInfo,textFields,labels,buttons);
    }

    private void initiateContactInfoTab(Tab contactInfo){
        List<TextField> textFields = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();

        address = new TextField();textFields.add(address);
        phoneNumber = new TextField();textFields.add(phoneNumber);
        emailAddress = new TextField();textFields.add(emailAddress);

        addressLabel = new Label();labels.add(addressLabel);
        phoneNUmberLabel = new Label();labels.add(phoneNUmberLabel);
        emailAddressLabel = new Label();labels.add(emailAddressLabel);

        createContact = new Button("Create");buttons.add(createContact);
        createContact.setOnAction(e->handleContactInfoCreation());
        updateContact = new Button("Update");buttons.add(updateContact);
        updateContact.setOnAction(e->handleContactInfoUpdate());
        deleteContact = new Button("Delete");buttons.add(deleteContact);
        deleteContact.setOnAction(e->handleContactInfoDelete());

        StudentTabCreator.createContact(contactInfo,textFields,labels,buttons);
    }

    private void initiateEnrollmentTab(Tab enrollmentInfo){
        List<Label> labels = new ArrayList<>();
        courses = new ListView<>();
        courseName = new Label("info");labels.add(courseName);
        courseSession = new Label("info");labels.add(courseSession);
        courseExamDate = new Label("info");labels.add(courseExamDate);
        searchField = new TextField();
        keyField = new TextField();
        searchButton = new Button("Search");
        searchButton.setOnAction(e->handleSearchOperation());
        enrollButton = new Button("Enroll me");
        enrollButton.setOnAction(e->handleEnrollmentOperation());
        StudentTabCreator.createCourse(enrollmentInfo,searchField,keyField,labels,searchButton,enrollButton,courses);
    }

    private void handlePersonalInfoCreation(){

    }

    private void handlePersonalInfoUpdate(){

    }

    private void handleStudentInfoCreation(){

    }

    private void handleStudentInfoUpdate(){

    }

    private void handleStudentInfoDelete(){

    }

    private void handleContactInfoCreation(){}

    private void handleContactInfoUpdate(){}

    private void handleContactInfoDelete(){}

    private void handleSearchOperation(){}

    private void handleEnrollmentOperation(){}
}
