import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.util.Duration;


public class DoctorController {
    public TableView patientsTable;
    public TableView incomeTable;
    private Timeline timeline;


    @FXML
    public void initialize(){
        timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {
            updateIncomeTable();
            updatePatientTable();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void updatePatientTable(){
        System.out.println("update patient table");
    }

    private void updateIncomeTable(){
        System.out.println("update income table");
    }

    void stopTimeLine(){
        timeline.stop();
    }
}
