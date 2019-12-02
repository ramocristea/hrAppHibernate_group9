package org.sda.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "salary_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    private Long salary;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public Salary() {
    }

    public Salary(Employee employee, Long salary, Date fromDate, Date toDate) {
        this.employee = employee;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Employee getEmployeeNumber() {
        return employee;
    }

    public void setEmployeeNumber(Employee employee) {
        this.employee = employee;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

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

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employee=" + employee.getNumber() +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
