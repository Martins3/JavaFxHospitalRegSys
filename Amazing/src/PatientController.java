
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class PatientController {
    public TextField DepartmentName;
    public TextField DoctorName;
    public TextField RegistrationType;
    public TextField RegistrationName;
    public TextField Money;
    public TextField ActualMoney;
    public TextField Exchange;
    public TextField RegistrationNum;
    public ComboBox<String> regType;
    public TextField payment;
    public TextField exchangeCount;
    public Label regNum;
    public ComboBox<String> doctorName;
    private Main main;

    @FXML
    public void initialize(){
        // 显示所有科室的“科室编号”、“科室名称”和“拼音字首”
        // 每种号种挂号限定当日人次，挂号人数超过规定数量不得挂
        // 若病人有预存金额则直接扣除挂号费，此时“交款金额”和“找零金额”处于灰色不可操作状态
        regType.getItems().addAll( "普通", "专家");
        regType.getEditor().textProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Text changed"));
        regType.getSelectionModel().selectFirst();

        doctorName.getItems().addAll("a", "b", "c");


        // 不可选的问题
        payment.setVisible(false);
        exchangeCount.setEditable(false);

        // 初始化工作

        // 不同选择框的处理的逐步筛选的工作

        // 收钱的处理

        // 动态变化的框图的筛选函数 : 再次建立几个类用于处理文档
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void ok(MouseEvent mouseEvent) {

    }

    public void clear(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

        System.out.println("show doctor name");
        doctorName.show();
    }


    private ArrayList<String> matchQuery(String input, String table){
        return null;
    }

    private boolean isMatch(String input, String abbr){
        return false;
    }
}
