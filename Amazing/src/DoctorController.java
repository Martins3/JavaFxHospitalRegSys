import com.hibernate.data.ePatientEntity;
import com.hibernate.data.eRegistrationInstanceEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class DoctorController {
    public TableView<PatRow> patientsTable;
    public TableView incomeTable;
    public DatePicker startDataPicker;
    public DatePicker endDataPicker;
    public TableColumn<PatRow, String> pRegNum;
    public TableColumn pPatNum;
    public TableColumn pDate;
    public TableColumn pExpert;
    public Tab pTab;

    // update thread !
    private Timeline timeline;
    private Main main;

    private ObservableList<PatRow> patData = FXCollections.observableArrayList();

    public void setMain(Main main) {
        this.main = main;
    }

    public void exit(MouseEvent mouseEvent) {
        main.setMain();
    }


    class PatRow{
        private SimpleStringProperty regNum;
//        private  SimpleStringProperty  patNum;
//        private   SimpleStringProperty date;
//        private   SimpleStringProperty expert;

        PatRow(String regNum) {
            this.regNum = new SimpleStringProperty(regNum);
        }



        StringProperty getRegNum() {
            return regNum;
        }
    }


    @FXML
    public void initialize(){
        pRegNum.setCellValueFactory(cellData -> cellData.getValue().getRegNum());
        patientsTable.getItems().add(new PatRow("ggggggg"));

//        patientsTable.setItems(patData);
//        pPatNum.setCellValueFactory(new PropertyValueFactory<PatRow, String>("patNum"));
//        pDate.setCellValueFactory(new PropertyValueFactory<PatRow, String>("date"));
//        pExpert.setCellValueFactory(new PropertyValueFactory<PatRow, String>("expert"));

        timeline = new Timeline(
        new KeyFrame(Duration.seconds(1000), e -> {
            updateIncomeTable();
            updatePatientTable();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void updatePatientTable(){
        System.out.println("update patient table");

        Session session = DBMain.getSession();
        String hql = "FROM eRegistrationInstanceEntity";
        Query query = session.createQuery(hql);
        List<eRegistrationInstanceEntity> results = query.list();
        for(eRegistrationInstanceEntity e: results){

        }
    }

    private String getPatientName(String id){
        Session session = DBMain.getSession();
        String hql = "FROM ePatientEntity where :num = id";
        Query query = session.createQuery(hql);
        query.setParameter("num", id);
        List<ePatientEntity> results = query.list();
        ePatientEntity e = results.get(0);
        return e.getName();
    }

    private void updateIncomeTable(){
        System.out.println("update income table");
    }

    void stopTimeLine(){
        timeline.stop();
    }
}
