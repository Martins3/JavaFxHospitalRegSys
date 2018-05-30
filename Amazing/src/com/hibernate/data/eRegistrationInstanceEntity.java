package com.hibernate.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Registration_Instance", schema = "Hospital", catalog = "")
public class eRegistrationInstanceEntity {
    private String num;
    private int patientAmount;
    private byte isCancelled;
    private BigDecimal actualCost;
    private Timestamp time;

    @Id
    @Column(name = "num", nullable = false, length = 6)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "Patient_amount", nullable = false)
    public int getPatientAmount() {
        return patientAmount;
    }

    public void setPatientAmount(int patientAmount) {
        this.patientAmount = patientAmount;
    }

    @Basic
    @Column(name = "is_cancelled", nullable = false)
    public byte getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(byte isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Basic
    @Column(name = "actual_cost", nullable = false, precision = 2)
    public BigDecimal getActualCost() {
        return actualCost;
    }

    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        eRegistrationInstanceEntity that = (eRegistrationInstanceEntity) o;
        return patientAmount == that.patientAmount &&
                isCancelled == that.isCancelled &&
                Objects.equals(num, that.num) &&
                Objects.equals(actualCost, that.actualCost) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(num, patientAmount, isCancelled, actualCost, time);
    }
}
