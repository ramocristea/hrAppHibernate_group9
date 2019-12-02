package org.sda.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dept_emp_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "id=" + id +
                ", department=" + department.getName() +
                ", employee=" + employee.getNumber() +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
