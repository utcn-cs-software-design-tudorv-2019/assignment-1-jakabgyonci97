package pl.controller;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class AdminTabCreator {
    protected static void createCRUDer(Tab tab, List<TextField> textFields, List<Button> buttons, TableView<Object> students,ProgressBar fullInfo){
        Pane layout = new Pane();
        tab.setContent(layout);

        students.setLayoutX(20);
        students.setLayoutY(20);
        layout.getChildren().add(students);

        Label label = new Label("Student Information");
        label.setLayoutX(780);
        label.setLayoutY(50);
        layout.getChildren().add(label);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("Identification Number"));
        localLabels.add(new Label("First Name"));
        localLabels.add(new Label("Last Name"));
        localLabels.add(new Label("Group"));
        localLabels.add(new Label("Average"));
        localLabels.add(new Label("Scholarship Status"));
        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(780);
            localLabels.get(i).setLayoutY(155 + i*50);
        }
        layout.getChildren().addAll(localLabels);

        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setLayoutX((960));
            textFields.get(i).setLayoutY((150 + i*50));
        }
        layout.getChildren().addAll(textFields);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setLayoutX(720);
        sep.setLayoutY(0);
        sep.setPrefHeight(620);
        layout.getChildren().add(sep);

        sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(620);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        buttons.get(0).setLayoutX(300);
        buttons.get(0).setLayoutY(680);
        for(int i=1;i<buttons.size();i++){
            buttons.get(i).setLayoutX(620 + i*150);
            buttons.get(i).setLayoutY(680);
        }
        layout.getChildren().addAll(buttons);

        fullInfo.setLayoutX(780);
        fullInfo.setLayoutY(580);
        fullInfo.setPrefWidth(360);
        layout.getChildren().add(fullInfo);

    }

    protected static void createRepGen(Tab tab,TextField searchField,List<Label> labels,DatePicker start,DatePicker end,Button searchButton,Button filterButton,ListView<String> activities){
        Pane layout = new Pane();
        tab.setContent(layout);

        Label label = new Label("Search for student");
        label.setLayoutX(60);
        label.setLayoutY(30);
        layout.getChildren().add(label);

        searchField.setLayoutX(60);
        searchField.setLayoutY(80);
        searchField.setPrefWidth(900);
        searchField.setPromptText("enter student identification number ex.2013518");
        layout.getChildren().add(searchField);

        List<Label> localLabels = new ArrayList<>();
        localLabels.add(new Label("Last Name"));
        localLabels.add(new Label("First Name"));
        localLabels.add(new Label("Group"));
        localLabels.add(new Label("Filter Search"));
        localLabels.add(new Label("Start Date"));
        localLabels.add(new Label("End Date"));

        for(int i=0;i<localLabels.size();i++){
            localLabels.get(i).setLayoutX(60);
            localLabels.get(i).setLayoutY(200 + i*70);
            if(i >= 3)
                localLabels.get(i).setLayoutY(230 + i*80);
        }
        layout.getChildren().addAll(localLabels);

        for(int i=0;i<labels.size();i++){
            labels.get(i).setLayoutX(200);
            labels.get(i).setLayoutY(200 + i*70);
        }
        layout.getChildren().addAll(labels);

        start.setLayoutX(150);
        start.setLayoutY(545);
        end.setLayoutX(150);
        end.setLayoutY(625);
        layout.getChildren().addAll(start,end);

        activities.setLayoutX(450);
        activities.setLayoutY(200);
        activities.setPrefSize(710,500);
        layout.getChildren().add(activities);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(0);
        sep.setLayoutY(150);
        sep.setPrefWidth(1200);
        layout.getChildren().add(sep);

        searchButton.setLayoutX(1020);
        searchButton.setLayoutY(80);
        filterButton.setLayoutX(220);
        filterButton.setLayoutY(680);
        layout.getChildren().addAll(searchButton,filterButton);
    }
}
