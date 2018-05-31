
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private DoctorController doctorController;

    private String userID;


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


    @Override
    public void stop(){
        if(doctorController != null) doctorController.stopTimeLine();
        DBMain.closeDBConnection();
        if(primaryStage != null) primaryStage.close();
    }

    void setPatient(String id){
        FXMLLoader patientScene = new FXMLLoader(getClass().getResource("layout/Patient.fxml"));
        try {
            Parent p = patientScene.load();
            PatientController patientController = patientScene.getController();
            patientController.setUser(id);
            patientController.rendering();
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
            doctorController = doctorScene.getController();
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

