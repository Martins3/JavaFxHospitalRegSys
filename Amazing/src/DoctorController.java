import com.hibernate.data.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;


public class DoctorController {
    public TableView<PatRow> patientsTable;
    public TableView<Income> incomeTable;
    public DatePicker startDataPicker;
    public DatePicker endDataPicker;
    public TableColumn<PatRow, String> pRegNum;
    public TableColumn<PatRow, String> pPatNum;
    public TableColumn<PatRow, String> pDate;
    public TableColumn<PatRow, String> pExpert;

    public TableColumn<Income, String> iDepartmentName;
    public TableColumn<Income, String> iDoctorNum;
    public TableColumn<Income, String> iDoctorName;
    public TableColumn<Income, String> iRegType;
    public TableColumn<Income, String> iPatientAmount;
    public TableColumn<Income, String> iTotalIncome;


    // update thread !
    private Timeline timeline;
    private Main main;
    private String user;


    private Timestamp start;
    private Timestamp end;


    class PatRow{
        private SimpleStringProperty regNum;
        private  SimpleStringProperty  patNum;
        private   SimpleStringProperty date;


        private   SimpleStringProperty expert;

        PatRow(String regNum, String patNum, String date, String expert) {
            this.regNum = new SimpleStringProperty(regNum);
            this.patNum = new SimpleStringProperty(patNum);
            this.date = new SimpleStringProperty(date);
            this.expert = new SimpleStringProperty(expert);
        }

        SimpleStringProperty getRegNum() {
            return regNum;
        }


        SimpleStringProperty getPatNum() {
            return patNum;
        }


        SimpleStringProperty getDate() {
            return date;
        }


        SimpleStringProperty getExpert() {
            return expert;
        }
    }

    class Income{
        private SimpleStringProperty departmentName;
        private SimpleStringProperty doctorNum;
        private SimpleStringProperty doctorName;
        private SimpleStringProperty regType;
        private SimpleStringProperty patientAmount;
        private SimpleStringProperty income;

        Income(String departmentName, String doctorNum, String doctorName,
               String regType, String patientAmount, String income) {
            this.departmentName = new SimpleStringProperty(departmentName);
            this.doctorNum = new SimpleStringProperty(doctorNum);
            this.doctorName = new SimpleStringProperty(doctorName);
            this.regType = new SimpleStringProperty(regType);
            this.patientAmount = new SimpleStringProperty(patientAmount);
            this.income = new SimpleStringProperty(income);
        }

        SimpleStringProperty getDepartmentName() {
            return departmentName;
        }

        SimpleStringProperty getDoctorName() {
            return doctorName;
        }

        SimpleStringProperty getDoctorNum() {
            return doctorNum;
        }

        SimpleStringProperty getPatientAmount() {
            return patientAmount;
        }

        SimpleStringProperty getRegType() {
            return regType;
        }

        SimpleStringProperty getIncome() {
            return income;
        }
    }


    @FXML
    public void initialize(){

        startDataPicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            newValue += " 00:00:00";
            start = Timestamp.valueOf(newValue);
            updateIncomeTable();
        });

        endDataPicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            newValue += " 00:00:00";
            end = Timestamp.valueOf(newValue);
            updateIncomeTable();
        });


        pRegNum.setCellValueFactory(cellData -> cellData.getValue().getRegNum());
        pPatNum.setCellValueFactory(cellData -> cellData.getValue().getPatNum());
        pExpert.setCellValueFactory(cellData -> cellData.getValue().getExpert());
        pDate.setCellValueFactory(cellData -> cellData.getValue().getDate());

        iDepartmentName.setCellValueFactory(cellData -> cellData.getValue().getDepartmentName());
        iDoctorName.setCellValueFactory(cellData -> cellData.getValue().getDoctorName());
        iDoctorNum.setCellValueFactory(cellData -> cellData.getValue().getDoctorNum());
        iRegType.setCellValueFactory(cellData -> cellData.getValue().getRegType());
        iPatientAmount.setCellValueFactory(cellData -> cellData.getValue().getPatientAmount());
        iTotalIncome.setCellValueFactory(cellData -> cellData.getValue().getIncome());

        timeline = new Timeline(
        new KeyFrame(Duration.seconds(60), e -> {
            updateIncomeTable();
            updatePatientTable();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }




    private void updatePatientTable(){
        System.out.println("update patient table !");
        patientsTable.getItems().clear();
        Session session = DBMain.getSession();
        String hql = "FROM eRegistrationInstanceEntity where DoctorNum = :user";
        Query query = session.createQuery(hql);
        query.setParameter("user", user);
        List<eRegistrationInstanceEntity> results = query.list();
        for(eRegistrationInstanceEntity e: results){
            String patientName = getPatientName(e.getPatientNum());
            String type = getRegType(e.getRegNum());
            patientsTable.getItems().add(new PatRow(e.getNum(), patientName,
                    e.getTime().toString(), type));
        }
    }

    private String getRegType(String regId){
        Session session = DBMain.getSession();
        String hql = "FROM eRegistrationTypeEntity where :num = id";
        Query query = session.createQuery(hql);
        query.setParameter("num", regId);
        List<eRegistrationTypeEntity> results = query.list();
        eRegistrationTypeEntity e = results.get(0);
        if(e.getIsExpert() == 1) return "专家";
        return "普通号";
    }

    private String getPatientName(String id){
        Session session = DBMain.getSession();
        String hql = "FROM ePatientEntity where num = :num";
        Query query = session.createQuery(hql);
        query.setParameter("num", id);
        List<ePatientEntity> results = query.list();
        ePatientEntity e = results.get(0);
        return e.getName();
    }

    private void updateIncomeTable(){
        System.out.println("update income table");
        // 显示所有科室不同医生不同号种起止日期内的收入合计
        // 起始日期不输入时默认为当天零时开始
        // 截止日期至当前时间为止
        incomeTable.getItems().clear();
        Session session = DBMain.getSession();
        String hql = "FROM eRegistrationInstanceEntity";
        Query query = session.createQuery(hql);
        List<eRegistrationInstanceEntity> results = query.list();
        for(eRegistrationInstanceEntity e: results){
            if((start == null || e.getTime().after(start)) && (end == null || e.getTime().before(end))){
                String departmentName = getDepartmentName(e.getRegNum());
                String docNum = e.getDoctorNum();
                String docName = getDoctorName(docNum);
                String regType = getRegType(e.getRegNum());
                String patAmount =  "" + e.getPatientAmount();
                String totalIncome = "" + (e.getPatientAmount() * e.getActualCost().intValue());
                Income i = new Income(departmentName, docNum, docName, regType, patAmount, totalIncome);
                incomeTable.getItems().add(i);
            }
        }
    }


    private String getDepartmentName(String regNum){
        // 查询 regType 表格 获取department_id
        Session session = DBMain.getSession();
        String hql = "FROM eRegistrationTypeEntity where num = :regNum";
        Query query = session.createQuery(hql);
        query.setParameter("regNum", regNum);
        List<eRegistrationTypeEntity> results = query.list();
        eRegistrationTypeEntity e = results.get(0);
        String d = e.getDepartmentNum();

        // 查询原来的文档
        hql = "from eDepartmentEntity  where num = :d";
        query = session.createQuery(hql);
        query.setParameter("d", d);
        List<eDepartmentEntity> dr = query.list();
        eDepartmentEntity de = dr.get(0);
        return de.getName();
    }

    private String getDoctorName(String docNum){
        // 查询 doctor 表格
        Session session = DBMain.getSession();
        String hql = "FROM eDoctorEntity where num = :docNum";
        Query query = session.createQuery(hql);
        query.setParameter("docNum", docNum);
        List<eDoctorEntity> results = query.list();
        eDoctorEntity e = results.get(0);
        return e.getName();
    }

    void stopTimeLine(){
        timeline.stop();
    }

    void setUser(String user) {
        this.user = user;
    }

    void setMain(Main main) {
        this.main = main;
    }

    public void exit(MouseEvent mouseEvent) {
        main.setMain();
    }

    void initTable(){
        Platform.runLater(() -> {
            updateIncomeTable();
            updatePatientTable();
        });
    }
}
