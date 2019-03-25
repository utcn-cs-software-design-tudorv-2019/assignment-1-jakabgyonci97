package pl.controller;

import bll.StudentBLL;
import dal.entity.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    //models
    private Student student;
    private PersonalInformation pi;
    private StudentInformation si;
    private ContactInformation ci;
    private Course course;

    //personal information
    private TextField firstName; private Label firstNameLabel;
    private TextField lastName; private Label lastNameLabel;
    private TextField icn; private Label icnLabel;
    private TextField pnc; private Label pncLabel;
    private Button createPersonal;

    //student information
    private TextField idStudent; private Label idStudentLabel;
    private TextField group; private Label groupLabel;
    private TextField scholarShip; private Label scholarShipLabel;
    private TextField grades; private Label gradesLabel;
    private Button createStudent;

    //contact information
    private TextField address; private Label addressLabel;
    private TextField phoneNumber; private Label phoneNUmberLabel;
    private TextField emailAddress; private Label emailAddressLabel;
    private Button createContact;

    //enrollment information
    private ListView<String> courses;
    private Label courseName;
    private Label courseSession;
    private Label courseExamDate;
    private TextField searchField;
    private TextField keyField;

    //error log
    private TextArea errorlog;

    public StudentController(Student student){
        this.student = student;
        pi = (new StudentBLL()).viewPersonalInfo(student);
        si = (new StudentBLL()).viewStudentInfo(student);
        ci = (new StudentBLL()).viewContactInfo(student);

        Stage window = new Stage();
        window.setTitle("Student Profile");
        errorlog = new TextArea();
        errorlog.setLayoutX(630);
        errorlog.setLayoutY(30);
        errorlog.setPrefSize(550,100);
        errorlog.setPromptText("Error messages will appear here");
        errorlog.setEditable(false);

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
        viewPersonalInfo(pi);
        viewStudentInfo(si);
        viewContactInfo(ci);
        viewEnrolledCourses();
    }

    /**StudentUI tab initializers*/
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
        Button updatePersonal = new Button("Update");buttons.add(updatePersonal);
        updatePersonal.setOnAction(e->handlePersonalInfoUpdate());

        StudentTabCreator.createPersonal(personalInfo,textFields,labels,buttons,errorlog);
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
        Button updateStudent = new Button("Update"); buttons.add(updateStudent);
        updateStudent.setOnAction(e->handleStudentInfoUpdate());
        Button deleteStudent = new Button("Delete"); buttons.add(deleteStudent);
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
        Button updateContact = new Button("Update");buttons.add(updateContact);
        updateContact.setOnAction(e->handleContactInfoUpdate());
        Button deleteContact = new Button("Delete");buttons.add(deleteContact);
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
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e->handleSearchOperation());
        Button enrollButton = new Button("Enroll me");
        enrollButton.setOnAction(e->handleEnrollmentOperation());
        StudentTabCreator.createCourse(enrollmentInfo,searchField,keyField,labels,searchButton,enrollButton,courses);
    }

    /**button actionListeners*/
    /**create/view/update student's personal information*/
    private void viewPersonalInfo(PersonalInformation pi){
        firstNameLabel.setText("No information yet");
        lastNameLabel.setText("No information yet");
        icnLabel.setText("No information yet");
        pncLabel.setText("No information yet");

        if(pi == null)return;
        else createPersonal.setVisible(false);

        if(!pi.getFirstName().isEmpty())
            firstNameLabel.setText(pi.getFirstName());
        if(!pi.getLastName().isEmpty())
            lastNameLabel.setText(pi.getLastName());
        if(!pi.getIcn().isEmpty())
            icnLabel.setText(pi.getIcn());
        if(!pi.getPnc().isEmpty())
            pncLabel.setText(pi.getPnc());
    }
    private void handlePersonalInfoCreation(){
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String icnText = icn.getText();
        String pncText = pnc.getText();
        cleanPersonalInfoTextFields();

        String message = (new StudentBLL()).createPersonalInfo(student,firstNameText,lastNameText,icnText,pncText);
        if(message != null)
            errorlog.setText(message);
        else{
            pi = (new StudentBLL()).viewPersonalInfo(student);
            viewPersonalInfo(pi);
        }
    }
    private void handlePersonalInfoUpdate(){
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String icnText = icn.getText();
        String pncText = pnc.getText();
        cleanPersonalInfoTextFields();

        String message = (new StudentBLL()).updatePersonalInfo(pi,firstNameText,lastNameText,icnText,pncText);
        if(message != null) errorlog.setText(message);
        else{
            pi = (new StudentBLL()).viewPersonalInfo(student);
            viewPersonalInfo(pi);
        }
    }
    private void cleanPersonalInfoTextFields(){
        firstName.clear();
        lastName.clear();
        icn.clear();
        pnc.clear();
    }

    /**create/view/update/delete student's student information*/
    private void viewStudentInfo(StudentInformation si){
        idStudentLabel.setText("No information yet");
        groupLabel.setText("No information yet");
        scholarShipLabel.setText("No information yet");
        gradesLabel.setText("No information yet");

        if(si == null) {createStudent.setVisible(true);return;}
        else createStudent.setVisible(false);

        idStudentLabel.setText(Integer.toString(si.getIdStudent()));
        if(!si.getGroup().isEmpty())
            groupLabel.setText(si.getGroup());
        scholarShipLabel.setText(si.getScholarShipState()+"");
        gradesLabel.setText(Double.toString(si.getGradeAvrg()));
    }
    private void handleStudentInfoCreation(){
        String groupText = group.getText();
        String scholarShipText = scholarShip.getText();
        String gradesText = grades.getText();
        cleanStudentInfoTextFields();

        String message = (new StudentBLL()).createStudentInfo(student,groupText,scholarShipText,gradesText);
        if(message != null) errorlog.setText(message);
        else{
            si = (new StudentBLL()).viewStudentInfo(student);
            viewStudentInfo(si);
        }
    }
    private void handleStudentInfoUpdate(){
        String groupText = group.getText();
        String scholarShipText = scholarShip.getText();
        String gradesText = grades.getText();
        cleanStudentInfoTextFields();

        String message = (new StudentBLL()).updateStudentInfo(si,groupText,scholarShipText,gradesText);

        if(message != null) errorlog.setText(message);
        else{
            si = (new StudentBLL()).viewStudentInfo(student);
            viewStudentInfo(si);
        }
    }
    private void handleStudentInfoDelete(){
        cleanStudentInfoTextFields();
        String message = (new StudentBLL()).deleteStudentInfo(si);
        if(message != null) errorlog.setText(message);
        else{
            si = (new StudentBLL()).viewStudentInfo(student);
            viewStudentInfo(si);
        }
    }
    private void cleanStudentInfoTextFields(){
        idStudent.clear();
        group.clear();
        scholarShip.clear();
        grades.clear();
    }

    /**create/view/update/delete student's contact information*/
    private void viewContactInfo(ContactInformation ci){
        addressLabel.setText("No information yet");
        phoneNUmberLabel.setText("No information yet");
        emailAddressLabel.setText("No information yet");

        if(ci == null) {createContact.setVisible(true);return;}
        else createContact.setVisible(false);

        if(!ci.getAddress().isEmpty())
            addressLabel.setText(ci.getAddress());
        if(!ci.getPhoneNumber().isEmpty())
            phoneNUmberLabel.setText(ci.getPhoneNumber());
        if(!ci.getEmailAddress().isEmpty())
            emailAddressLabel.setText(ci.getEmailAddress());
    }
    private void handleContactInfoCreation(){
        String addressText = address.getText();
        String phoneNumberText = phoneNumber.getText();
        String emailAddressText = emailAddress.getText();
        cleanContactInfoTextFields();

        String message = (new StudentBLL()).createContactInfo(student,addressText,phoneNumberText,emailAddressText);
        if(message != null) errorlog.setText(message);
        else {
            ci = (new StudentBLL()).viewContactInfo(student);
            viewContactInfo(ci);
        }
    }
    private void handleContactInfoUpdate(){
        String addressText = address.getText();
        String phoneNumberText = phoneNumber.getText();
        String emailAddressText = emailAddress.getText();
        cleanContactInfoTextFields();

        String message = (new StudentBLL()).updateContactInfo(ci,addressText,phoneNumberText,emailAddressText);
        if(message != null) errorlog.setText(message);
        else {
            ci = (new StudentBLL()).viewContactInfo(student);
            viewContactInfo(ci);
        }
    }
    private void handleContactInfoDelete(){
        cleanContactInfoTextFields();
        String message = (new StudentBLL()).deleteContactInfo(ci);
        if(message != null) errorlog.setText(message);
        else {
            ci = (new StudentBLL()).viewContactInfo(student);
            viewContactInfo(ci);
        }
    }
    private void cleanContactInfoTextFields(){
        address.clear();
        phoneNumber.clear();
        emailAddress.clear();
    }

    /**process student enrollment - search for course, enroll student for course*/
    private void handleSearchOperation(){
        String searchCourseSession = searchField.getText();
        course = (new StudentBLL()).searchForCourse(searchCourseSession);
        cleanCourseSearch();
        if(course == null) return;
        if(!course.getName().isEmpty())
            courseName.setText(course.getName());
        if(!course.getSession().isEmpty())
            courseSession.setText(course.getSession());
        if(!course.getExamDate().toString().isEmpty())
            courseExamDate.setText(course.getExamDate().toString());

    }
    private void handleEnrollmentOperation(){
        String enrollmentKeyText = keyField.getText();
        cleanCourseSearch();
        String message = (new StudentBLL()).processEnrollment(student,course,enrollmentKeyText);
        if(message != null) errorlog.setText(message);
        else viewEnrolledCourses();
    }
    private void viewEnrolledCourses(){
        ObservableList<String> enrolledCourses = (new StudentBLL()).findEnrolledCourses(student);
        courses.setEditable(false);
        courses.setItems(enrolledCourses);
    }
    private void cleanCourseSearch(){
        searchField.clear();
        keyField.clear();
        courseName.setText("Course not found!");
        courseSession.setText("Course not found!");
        courseExamDate.setText("Course not found!");
    }
}
