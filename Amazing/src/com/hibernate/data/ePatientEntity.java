package com.hibernate.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Patient", schema = "Hospital", catalog = "")
public class ePatientEntity {
    private String num;
    private String name;
    private String password;
    private BigDecimal money;
    private Timestamp signUpTime;

    @Id
    @Column(name = "num", nullable = false, length = 6)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 8)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 2)
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Basic
    @Column(name = "sign_up_time", nullable = true)
    public Timestamp getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(Timestamp signUpTime) {
        this.signUpTime = signUpTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ePatientEntity that = (ePatientEntity) o;
        return Objects.equals(num, that.num) &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(money, that.money) &&
                Objects.equals(signUpTime, that.signUpTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(num, name, password, money, signUpTime);
    }
}
