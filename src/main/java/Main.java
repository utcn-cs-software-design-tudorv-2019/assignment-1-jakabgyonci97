import javafx.application.Application;
import javafx.stage.Stage;
import pl.controller.AdminController;
import pl.controller.LoginController;
import pl.controller.StudentController;


public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController lc = new LoginController();
        //StudentController sc = new StudentController();
        //AdminController ac = new AdminController();

    }

}
