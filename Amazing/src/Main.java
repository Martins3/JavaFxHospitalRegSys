
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        FXMLLoader root = new FXMLLoader(getClass().getResource("layout/Login.fxml"));
        Pane pane = root.load();
        Login rootController = root.getController();

        rootController.setMain(this);

        primaryStage.setTitle("登录界面");
        primaryStage.setScene(new Scene(pane));

        primaryStage.show();
    }

    void setPatient(){
        FXMLLoader patientScene = new FXMLLoader(getClass().getResource("layout/Patient.fxml"));
        try {
            Parent p = patientScene.load();
            PatientController patientController = patientScene.getController();
            patientController.setMain(this);
            primaryStage.setTitle("挂号");
            primaryStage.setScene(new Scene(p, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void setDoctor(){
        FXMLLoader doctorScene = new FXMLLoader(getClass().getResource("layout/Doctor.fxml"));
        try {
            Pane p = doctorScene.load();
            primaryStage.setTitle("医生界面");
            primaryStage.setScene(new Scene(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

