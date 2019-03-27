package pl;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class StudentTabCreator {
    protected static void createPersonal(Tab tab, List<TextField> textFields, List<Label> labels, List<Button> buttons,TextArea errorlog){
        Pane layout = new Pane();
        tab.setContent(layout);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("FirstName"));
        localLabels.add(new Label("LastName"));
        localLabels.add(new Label("Identity Card Number"));
        localLabels.add(new Label("Personal Numerical Number"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(65);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        localLabels.clear();
        localLabels.add(new Label("FirstName"));
        localLabels.add(new Label("LastName"));
        localLabels.add(new Label("Identity Card Number"));
        localLabels.add(new Label("Personal Numerical Number"));
        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(650);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(220);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setLayoutX(560);
        sep.setLayoutY(220);
        sep.setPrefHeight(580);
        layout.getChildren().add(sep);

        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setLayoutX(900);
            textFields.get(i).setLayoutY(300+i*50);
            textFields.get(i).setMinWidth(250);
        }
        layout.getChildren().addAll(textFields);

        for(int i=0;i<labels.size();i++){
            labels.get(i).setText("info");
            labels.get(i).setLayoutX(300);
            labels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(labels);

        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setLayoutX(900 + 150*i);
            buttons.get(i).setLayoutY(700);
        }
        layout.getChildren().addAll(buttons);

        ImageView img = new ImageView(new Image("https://xvp.akamaized.net/assets/illustrations/happy-laptop-user-girl-ce42c1b1997f0128b89c6c25c55c3446.png"));
        img.setFitHeight(200);
        img.setFitWidth(200);
        img.setLayoutX(65);
        img.setLayoutY(10);
        layout.getChildren().add(img);

        layout.getChildren().add(errorlog);
    }

    protected static void createStudent(Tab tab,List<TextField> textFields,List<Label> labels,List<Button> buttons){
        Pane layout = new Pane();
        tab.setContent(layout);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("Student Identification Number"));
        localLabels.add(new Label("Group"));
        localLabels.add(new Label("Scholarship State"));
        localLabels.add(new Label("Average"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(65);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        localLabels.clear();
        localLabels.add(new Label("Student Identification Number"));
        localLabels.add(new Label("Group"));
        localLabels.add(new Label("Scholarship State"));
        localLabels.add(new Label("Average"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(650);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(220);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setLayoutX(560);
        sep.setLayoutY(220);
        sep.setPrefHeight(580);
        layout.getChildren().add(sep);

        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setLayoutX(900);
            textFields.get(i).setLayoutY(300+i*50);
            textFields.get(i).setMinWidth(250);
        }
        layout.getChildren().addAll(textFields);

        for(int i=0;i<labels.size();i++){
            labels.get(i).setText("info");
            labels.get(i).setLayoutX(300);
            labels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(labels);

        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setLayoutX(660 + 150*i);
            buttons.get(i).setLayoutY(700);
        }
        layout.getChildren().addAll(buttons);

        ImageView img = new ImageView(new Image("https://lh3.googleusercontent.com/kFR0CrwseuTWAXoCbYVSSiBT_v2X2HZ46XVlWEZ8EXIX_Kw5xPq5huWHEdG8hjD3Hg"));
        img.setFitHeight(200);
        img.setFitWidth(200);
        img.setLayoutX(65);
        img.setLayoutY(10);
        layout.getChildren().add(img);
    }

    protected static void createContact(Tab tab,List<TextField> textFields,List<Label> labels,List<Button> buttons){
        Pane layout = new Pane();
        tab.setContent(layout);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("Address"));
        localLabels.add(new Label("Phone number"));
        localLabels.add(new Label("Email Address"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(65);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        localLabels.clear();
        localLabels.add(new Label("Address"));
        localLabels.add(new Label("Phone number"));
        localLabels.add(new Label("Email Address"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(650);
            localLabels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(220);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setLayoutX(560);
        sep.setLayoutY(220);
        sep.setPrefHeight(580);
        layout.getChildren().add(sep);

        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setLayoutX(900);
            textFields.get(i).setLayoutY(300+i*50);
            textFields.get(i).setMinWidth(250);
        }
        layout.getChildren().addAll(textFields);

        for(int i=0;i<labels.size();i++){
            labels.get(i).setText("info");
            labels.get(i).setLayoutX(300);
            labels.get(i).setLayoutY(300 + i*50);
        }
        layout.getChildren().addAll(labels);

        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setLayoutX(660 + 150*i);
            buttons.get(i).setLayoutY(700);
        }
        layout.getChildren().addAll(buttons);

        ImageView img = new ImageView(new Image("https://thecolorfulcase.com/tcc/wp-content/uploads/2017/05/TCC-Contact-Icon-e1506176676566.png"));
        img.setFitHeight(200);
        img.setFitWidth(200);
        img.setLayoutX(65);
        img.setLayoutY(10);
        layout.getChildren().add(img);
    }

    protected static void createCourse(Tab tab,TextField searchField,TextField keyField,List<Label> labels,Button searchButton,Button enrollButton,ListView<String> courses){
        Pane layout = new Pane();
        tab.setContent(layout);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(220);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setLayoutX(780);
        sep.setLayoutY(220);
        sep.setPrefHeight(580);
        layout.getChildren().add(sep);

        Label label = new Label("My Courses");
        label.setLayoutX(65);
        label.setLayoutY(250);
        layout.getChildren().add(label);

        courses.setLayoutX(65);
        courses.setLayoutY(300);
        courses.setPrefWidth(600);
        layout.getChildren().add(courses);

        label = new Label("Search for new course");
        label.setLayoutX(340);
        label.setLayoutY(50);
        layout.getChildren().add(label);

        label = new Label("Course Information");
        label.setLayoutX(830);
        label.setLayoutY(250);
        layout.getChildren().add(label);

        label = new Label("Enter enrollment key");
        label.setLayoutX(860);
        label.setLayoutY(620);
        layout.getChildren().add(label);

        searchField.setLayoutX(340);
        searchField.setLayoutY(90);
        searchField.setPrefWidth(600);
        keyField.setLayoutX(860);
        keyField.setLayoutY(650);
        keyField.setPrefWidth(300);
        layout.getChildren().addAll(searchField,keyField);

        searchButton.setLayoutX(1040);
        searchButton.setLayoutY(90);
        enrollButton.setLayoutX(860);
        enrollButton.setLayoutY(700);
        layout.getChildren().addAll(searchButton,enrollButton);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("Name :"));
        localLabels.add(new Label("Session :"));
        localLabels.add(new Label("Date of exam :"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(830);
            localLabels.get(i).setLayoutY(300 + i*100);
        }
        layout.getChildren().addAll(localLabels);

        for(int i=0;i<labels.size();i++){
            labels.get(i).setLayoutX(860);
            labels.get(i).setLayoutY(350 + i*100);
        }
        layout.getChildren().addAll(labels);

        ImageView img = new ImageView(new Image("https://ramicompressori.it/wp-content/uploads/2018/04/contract.png"));
        img.setFitHeight(200);
        img.setFitWidth(200);
        img.setLayoutX(65);
        img.setLayoutY(10);
        layout.getChildren().add(img);
    }
}
