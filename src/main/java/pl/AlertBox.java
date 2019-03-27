package pl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertBox {
    Stage window;
    public AlertBox(String message){
        window = new Stage();
        window.initStyle(StageStyle.TRANSPARENT);
        window.initModality(Modality.APPLICATION_MODAL);
        Pane layout = new Pane();
        ImageView img = new ImageView(new Image("http://www.emn.fr/x-info/atlanmod/images/5/52/Alert-icon.png"));
        img.setFitWidth(150);
        img.setFitHeight(150);
        img.setLayoutX(225);
        img.setLayoutY(30);
        layout.getChildren().add(img);

        TextArea text = new TextArea();
        text.setEditable(false);
        text.setText(message);
        text.setPrefSize(550,130);
        text.setLayoutX(30);
        text.setLayoutY(200);
        layout.getChildren().add(text);

        Button close = new Button("Close");
        close.setLayoutX(260);
        close.setLayoutY(350);
        close.setOnAction(e->handleCloseOperation());
        layout.getChildren().add(close);

        Scene scene = new Scene(layout,600,400);
        scene.getStylesheets().add("DarkTheme.css");
        window.setScene(scene);
        window.showAndWait();
    }

    public void handleCloseOperation(){
        window.close();
    }

}
