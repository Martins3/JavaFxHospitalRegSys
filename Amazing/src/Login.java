
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
    private ToggleGroup toggleGroup;


    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        rbPatient.setToggleGroup(toggleGroup);
        rbDoctor.setToggleGroup(toggleGroup);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void tryLogin(MouseEvent mouseEvent) {
        if(rbPatient.isSelected()){
            if(true || loginQuery(userName.getText(), passWord.getText()))
                main.setPatient();
        }else if(rbDoctor.isSelected()){
            if(true || loginQuery(userName.getText(), passWord.getText()))
                main.setDoctor();
        }else{
            System.out.println("Nobody is chosen !");
        }
    }

//    public void trySignUp(MouseEvent mouseEvent) {
//        if(signUpUpdate(username.getText(), password.getText()))
//        main.setPatient();
//    }

    private boolean loginQuery(String id, String password){
        Session session = DBMain.getSession();
        String hql = "FROM ePatientEntity where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<ePatientEntity> results = query.list();

        if(results.size() == 0) return false;
        String patPass= results.get(0).getPassword();
        if(patPass == null){
            System.out.println("数据库中间没有该人");
            return false;
        }else{
            if(!patPass.equals(password)){
                System.out.println("账号密码错误");
                return false;
            }
        }
        return true;
    }

//    private boolean signUpUpdate(String id, String password){
//        Session session = DBMain.getSession();
//        session.beginTransaction();
//        ePatientEntity e = new ePatientEntity();
//        e.setNum(id);
//        e.setName("666");
//        e.setPassword(password);
//        e.setMoney(new BigDecimal(0));
//        e.setSignUpTime(Timestamp.valueOf(LocalDateTime.now()));
//        session.save(e);
//        session.getTransaction().commit();
//        return true;
//    }

    public void cancel(MouseEvent mouseEvent) {
        main.stop();
    }
}
