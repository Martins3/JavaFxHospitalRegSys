package com.hibernate.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Doctor", schema = "Hospital", catalog = "")
public class eDoctorEntity {
    private String num;
    private String name;
    private String abbr;
    private String password;
    private String departmentNum;
    private byte isExpert;
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
    @Column(name = "abbr", nullable = false, length = 4)
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
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
    @Column(name = "is_expert", nullable = false)
    public byte getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(byte isExpert) {
        this.isExpert = isExpert;
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
        eDoctorEntity that = (eDoctorEntity) o;
        return isExpert == that.isExpert &&
                Objects.equals(num, that.num) &&
                Objects.equals(name, that.name) &&
                Objects.equals(abbr, that.abbr) &&
                Objects.equals(password, that.password) &&
                Objects.equals(signUpTime, that.signUpTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(num, name, abbr, password, isExpert, signUpTime);
    }


    @Basic
    @Column(name = "Department_num", nullable = false)
    public String getDepartmentNum() {
        return departmentNum;
    }

    @Basic
    @Column(name = "Department_num", nullable = false)
    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }
}
