package com.hibernate.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Department", schema = "Hospital", catalog = "")
public class eDepartmentEntity {
    private String num;
    private String name;
    private String abbr;

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
    @Column(name = "abbr", nullable = false, length = 8)
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        eDepartmentEntity that = (eDepartmentEntity) o;
        return Objects.equals(num, that.num) &&
                Objects.equals(name, that.name) &&
                Objects.equals(abbr, that.abbr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(num, name, abbr);
    }
}
