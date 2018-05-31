
import com.hibernate.data.eDoctorEntity;
import com.hibernate.data.ePatientEntity;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Login {
    public TextField userName;
    public PasswordField passWord;
    public RadioButton rbPatient;
    public RadioButton rbDoctor;
    private Main main;
    private  boolean isPatient;


    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rbPatient.setToggleGroup(toggleGroup);
        rbDoctor.setToggleGroup(toggleGroup);
        rbPatient.fire();
        userName.setText("000001");
        passWord.setText("1");
        isPatient = true;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void tryLogin(MouseEvent mouseEvent) {
        if(loginQuery(userName.getText(), passWord.getText())){
            main.setUserID(userName.getText());
            if(isPatient)
                main.setPatient(userName.getText());
            else
                main.setDoctor();
        }else{
            System.out.println("没有该病人或者账号密码错误");
        }
    }

/*
    public void trySignUp(MouseEvent mouseEvent) {
        if(signUpUpdate(username.getText(), password.getText()))
            main.setPatient();
    }
*/

    private boolean loginQuery(String id, String password){
        Session session = DBMain.getSession();
        String hql;
        if(isPatient){
            hql = "FROM  ePatientEntity where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<ePatientEntity> results = query.list();
            System.out.println("patient id is " + id);

            if(results.size() == 0) return false;
            String patPass= results.get(0).getPassword();
            if(patPass == null || !patPass.equals(password)){
                return false;
            }
            return true;
        }else{
            hql = "FROM  eDoctorEntity where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<eDoctorEntity> results = query.list();
            if(results.size() == 0) return false;
            String patPass= results.get(0).getPassword();
            if(patPass == null || !patPass.equals(password)){
                return false;
            }
            return true;
        }
    }

/*
    private boolean signUpUpdate(String id, String password){
        Session session = DBMain.getSession();
        session.beginTransaction();
        ePatientEntity e = new ePatientEntity();
        e.setNum(id);
        e.setName("666");
        e.setPassword(password);
        e.setMoney(new BigDecimal(0));
        e.setSignUpTime(Timestamp.valueOf(LocalDateTime.now()));
        session.save(e);
        session.getTransaction().commit();
        return true;
    }
*/

    public void cancel(MouseEvent mouseEvent) {
        main.stop();
    }

    public void choosePatient(MouseEvent mouseEvent) {
        isPatient = true;
        System.out.println("patient selected !");
    }

    public void chooseDoctor(MouseEvent mouseEvent) {
        isPatient = false;
        System.out.println("doctor selected !");
    }
}
