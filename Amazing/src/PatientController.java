
import com.hibernate.data.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * reg_type and doctor has no relation at all
 * but is_expert and department will affect both of them !
 *
 * ok button
 */
public class PatientController {
    public ComboBox<String> regType;
    public TextField payment;
    public Label exchangeCount;
    public Label regNum;
    public ComboBox<String> doctorName;
    public ComboBox<String> isExpert;
    public ComboBox<String> departmentName;
    public Label log;
    public Label fee;


    // when ok pressed, the value stored here
    private String doctorID;
    private String regTypeID;
    private String departmentID; // for shorten!
    private boolean isExpertID; // for shorten and uniform

    private String user;
    private Main main;

    void setUser(String id) {
        this.user = id;
    }

    void setMain(Main main) {
        this.main = main;
    }

    void rendering(){
        Platform.runLater(() -> exchangeCount.setText("" + getDeposit()));
    }

    enum Confirm{
        /**
         * every confirm will shorten other choose
         * if only one choice is left, the textfield should  be set !
         * */
        NOBODY, DEPARTMENT, DOCTOR, REG_TYPE
    }


    @FXML
    public void initialize(){
        isExpertID = false;
        updateThreeCombo();
        initSelectListener();
        initDynamicShow();
        initExpertCombo();
        updateRes();
    }


    private void initExpertCombo(){
        isExpert.getItems().addAll( "未选择", "普通", "专家");
        isExpert.getSelectionModel().selectFirst();
        isExpert.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            isExpertID = t1.intValue() == 2;
            updateTwoCombo();
        });
    }

    private void initSelectListener(){
        /*
        doctorName.getEditor().focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
        if (newPropertyValue) { confirm = Confirm.DOCTOR; }
        });

        regType.getEditor().focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
        if (newPropertyValue) { confirm = Confirm.REG_TYPE; }
        });

        departmentName.getEditor().focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
        if (newPropertyValue) { confirm = Confirm.DEPARTMENT; }
        });
        */

        departmentName.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            if(!checkValid(t1, Confirm.DEPARTMENT))
                departmentID = null;
            updateTwoCombo();
        });

        regType.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            if(!checkValid(t1, Confirm.REG_TYPE))
                regTypeID = null;
            System.out.println("reg type id is" + regTypeID);
            updateRes();
        });

        doctorName.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            if(!checkValid(t1, Confirm.DOCTOR))
                doctorID = null;
            System.out.println("doctor id is " + doctorID);
            updateRes();
        });

    }

    private void initDynamicShow(){
        // ABBR
        doctorName.getEditor().setOnKeyTyped(ke -> {
            updateCombo(doctorName,doctorName.getEditor().getText());
            doctorName.show();
            departmentID = null;
        });

        doctorName.getEditor().setOnKeyTyped(ke -> {
               updateCombo(doctorName, doctorName.getEditor().getText());
               doctorName.show();
               doctorID = null;
        });

        regType.getEditor().setOnKeyTyped(ke -> {
            updateCombo(regType,regType.getEditor().getText());
            regType.show();
            regTypeID = null;
        });

        payment.setOnKeyPressed(ke -> {
            if(ke.getCode().equals(KeyCode.ENTER)){
                System.out.println("press enter !");
                try {
                    int pay = Integer.parseInt(payment.getText());
                    int deposit = getDeposit();
                    System.out.println("deposit is " + deposit);
                    int cost = Integer.valueOf(fee.getText());
                    if(pay + deposit < cost){
                        payment.clear();
                        System.out.println("pay is not enough " + pay  + " " + deposit);
                    }else{
                        deposit = pay + deposit  - cost;
                        exchangeCount.setText("" + deposit);
                        System.out.println("after " + getDeposit());
                    }
                }catch (NumberFormatException n){
                    payment.clear();
                }
            }
        });
    }

    private int getDeposit(){
        BigDecimal d = getPatient().getMoney();
        return d.intValue();
    }

    private void setDeposit(int d){
        Session session = DBMain.getSession();
        session.beginTransaction();
        ePatientEntity e = getPatient();
        e.setMoney(new BigDecimal(d));
        session.getTransaction().commit();
    }

    private ePatientEntity getPatient(){
        Session session = DBMain.getSession();
        String hql = "from  ePatientEntity where num = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("userID", user);
        List<ePatientEntity> l = query.list();
        return l.get(0);
    }

    /**
     * once the doctor and regType has been chosen
     * fee will be show and fee will be accessible
     * */
    private void updateRes(){
        if(doctorID == null || regTypeID == null){
            final String info = "医生或号种没有确定";
            fee.setText(info);
            payment.clear();
            payment.setVisible(false);
            return;
        }

        Session session = DBMain.getSession();
        String hql = "from  eRegistrationTypeEntity where num = :regTypeID";
        Query query = session.createQuery(hql);
        query.setParameter("regTypeID", regTypeID);
        List<eRegistrationTypeEntity> l = query.list();
        int cost = l.get(0).getMoney();
        fee.setText(cost + "");
        int deposit = getDeposit();
        if(cost > deposit){
            payment.setVisible(true);
        }
    }


    /**
     * combo box can receive enter, so we should check the input
     * tool function to check the id in the table
     * @param text the id to be searched, check the format at first
     * @return if in, return true
     */
    private boolean checkValid(String text, Confirm c){
        if(text == null) return false;
        // get the id from id
        if(text.length() < 6) return false;
        for (int i = 0; i < 6; i++) {
            if(text.charAt(i) < '0' || text.charAt(i) > '9') return false;
        }

        String id = text.substring(0, 6);
        System.out.println("the text is " + text);
        System.out.println("the id we get " + id);

        // query the table to make sure it exits
        Session session = DBMain.getSession();
        String hql = null;
        switch (c){
            case DOCTOR:
                hql = "FROM eDoctorEntity where num = :id";
                doctorID = id;
                break;
            case REG_TYPE:
                hql = "FROM eRegistrationTypeEntity where num = :id";
                regTypeID = id;
                break;
            case DEPARTMENT:
                hql = "FROM eDepartmentEntity where num = :id";
                departmentID = id;
                System.out.println("department id before query check" + id);
                break;
            case NOBODY:
                System.out.println("impossible !!!!!!!");
                return false;
        }

        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.list().size() == 1;
    }


    public void submit(MouseEvent mouseEvent) {
        // save changes
        setDeposit(Integer.valueOf(exchangeCount.getText()));

        // create a reg
        Session session = DBMain.getSession();
        session.beginTransaction();
        eRegistrationInstanceEntity e = new eRegistrationInstanceEntity();
        e.setNum("000001"); // how to make the data sync
        e.setRegNum("000003"); // add a variable
        e.setDoctorNum("000004"); // add a variable
        e.setPatientNum(user);
        e.setPatientAmount(12); // 如何确定人数

        e.setIsCancelled((byte)0);
        e.setActualCost(new BigDecimal(123));
        e.setTime(Timestamp.valueOf(LocalDateTime.now()));
        session.save(e);
        session.getTransaction().commit();
    }

    public void exit(MouseEvent mouseEvent) {
        main.stop();
    }


    private boolean isMatch(String abbr, String input){
        return abbr.contains(input);
    }

    /**
     * when update isExpert and department
     */
    private void updateTwoCombo(){
        updateCombo(regType, regType.getEditor().getText());
        updateCombo(doctorName, doctorName.getEditor().getText());
    }

    /**
     * when init
     */
    private void updateThreeCombo(){
        updateCombo(regType, "");
        updateCombo(doctorName, "");
        updateCombo(departmentName, "");
    }


    private void updateCombo(ComboBox<String> comboBox, String input){
        comboBox.getItems().clear();
        Session session = DBMain.getSession();
        List<String> list = new ArrayList<>();
        if(comboBox == doctorName){
            String hql = "FROM eDoctorEntity";
            Query query = session.createQuery(hql);
            List<eDoctorEntity> results = query.list();
            for(eDoctorEntity e : results){
                if(isMatch(e.getAbbr(), input)){
                    if(!isExpertID || e.getIsExpert() == 1){
                        if(departmentID == null || e.getDepartmentNum().equals(departmentID))
                            list.add(e.getNum() + " " + e.getName() + " " + e.getAbbr());
                    }
                }
            }
            comboBox.getItems().addAll(list);
        }else if(comboBox == regType){
            String hql = "FROM eRegistrationTypeEntity ";
            Query query = session.createQuery(hql);
            List<eRegistrationTypeEntity> results = query.list();
            for(eRegistrationTypeEntity e : results){
                if(isMatch(e.getAbbr(), input)){
                    if(!isExpertID || e.getIsExpert() == 1){
                        if(departmentID == null || e.getDepartmentNum().equals(departmentID))
                            list.add(e.getNum() + " " + e.getName() + " " + e.getAbbr());
                    }
                }
            }
            comboBox.getItems().addAll(list);
        }else if(comboBox == departmentName){
            String hql = "FROM eDepartmentEntity";
            Query query = session.createQuery(hql);
            List<eDepartmentEntity> results = query.list();
            for(eDepartmentEntity e : results){
                if(isMatch(e.getAbbr(), input))
                    list.add(e.getNum() + " " + e.getName() + " " + e.getAbbr());
            }
            comboBox.getItems().addAll(list);
        }
    }

}
