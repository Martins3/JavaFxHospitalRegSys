import com.hibernate.data.ePatientEntity;
import com.hibernate.data.eRegistrationInstanceEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class DoctorController {
    public TableView<PatRow> patientsTable;
    public TableView incomeTable;
    public DatePicker startDataPicker;
    public DatePicker endDataPicker;
    public TableColumn pRegNum;
    public TableColumn pPatNum;
    public TableColumn pDate;
    public TableColumn pExpert;

    // update thread !
    private Timeline timeline;

    private final ObservableList<PatRow> patData =
            FXCollections.observableArrayList(
                    new PatRow("E")
            );

    class PatRow{
        private SimpleStringProperty regNum;
//        private  SimpleStringProperty  patNum;
//        private   SimpleStringProperty date;
//        private   SimpleStringProperty expert;

        public PatRow(String regNum) {
            this.regNum = new SimpleStringProperty(regNum);
        }

        public void setRegNum(String regNum) {
            this.regNum.set(regNum);
        }

        public String getRegNum() {
            return regNum.get();
        }
    }


    @FXML
    public void initialize(){
        pRegNum.setCellValueFactory(new PropertyValueFactory<PatRow, String>("regNum"));
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
