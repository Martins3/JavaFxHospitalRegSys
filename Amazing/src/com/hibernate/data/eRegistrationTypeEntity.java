package com.hibernate.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Registration_Type", schema = "Hospital", catalog = "")
public class eRegistrationTypeEntity {
    private String num;
    private String name;
    private String abbr;
    private byte isExpert;
    private int numLimitaion;
    private byte money;

    @Id
    @Column(name = "num", nullable = false, length = 6)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 12)
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
    @Column(name = "is_expert", nullable = false)
    public byte getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(byte isExpert) {
        this.isExpert = isExpert;
    }

    @Basic
    @Column(name = "num_limitaion", nullable = false)
    public int getNumLimitaion() {
        return numLimitaion;
    }

    public void setNumLimitaion(int numLimitaion) {
        this.numLimitaion = numLimitaion;
    }

    @Basic
    @Column(name = "money", nullable = false)
    public byte getMoney() {
        return money;
    }

    public void setMoney(byte money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        eRegistrationTypeEntity that = (eRegistrationTypeEntity) o;
        return isExpert == that.isExpert &&
                numLimitaion == that.numLimitaion &&
                money == that.money &&
                Objects.equals(num, that.num) &&
                Objects.equals(name, that.name) &&
                Objects.equals(abbr, that.abbr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(num, name, abbr, isExpert, numLimitaion, money);
    }
}
