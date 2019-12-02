package org.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.sda.model.Department;
import org.sda.model.DepartmentEmployee;
import org.sda.model.Employee;
import org.sda.model.Salary;

import java.util.List;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        getAllDepartments();
//        getAllEmployees();

        Employee employee = getEmployeeById(11L);
        System.out.println(employee);

        employee.setFirstName("name updated");
        updateEmployee(employee);
        System.out.println(getEmployeeById(11L));

        employee.setNumber(35L);
//        insertEmployee(employee);
        System.out.println(getEmployeeById(35L));

        deleteEmployee(employee);
        System.out.println(getEmployeeById(35L));

        List<Employee> employees = getEmployeesByFirstName("Georgi");
        printList(employees);

        List<Salary> salaries = getAllSalariesForEmployeeId(10011L);
        printList(salaries);

        List<DepartmentEmployee> departmentsForEmployeeId = getDepartmentsForEmployeeId(10011L);
        printList(departmentsForEmployeeId);

        sessionFactory.close();
    }

    private static void getAllDepartments() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Department");

        List<Department> departments = query.list();
        printList(departments);

        session.close();
    }

    private static void getAllEmployees() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Employee");

        List<Employee> employees = query.list();
        printList(employees);

        session.close();
    }

    private static <T> void printList(List<T> list) {
        for(T t : list) {
            System.out.println(t);
        }
    }

    private static Employee getEmployeeById(Long id) {
        Session session = sessionFactory.openSession();

        Employee employee = session.find(Employee.class, id);

        session.close();

        return employee;
    }

    private static void updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(employee);

        transaction.commit();
        session.close();
    }

    private static void insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();
        session.close();
    }

    private static void deleteEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(employee);

        transaction.commit();
        session.close();
    }

    private static List<Employee> getEmployeesByFirstName(String name) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Employee as e where e.firstName=:firstName or e.lastName=:lastName");
        query.setParameter("firstName", name);
        query.setParameter("lastName", name);

        List<Employee> employees = query.list();

        session.close();

        return employees;
    }

    public static List<Salary> getAllSalariesForEmployeeId(Long id) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Salary as s where s.employee.number=:employeeId");
        query.setParameter("employeeId", id);

        List<Salary> salaries = query.list();

        session.close();

        return salaries;
    }

    private static List<DepartmentEmployee> getDepartmentsForEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from DepartmentEmployee as de where de.employee.number=:employeeId");
        query.setParameter("employeeId", employeeId);

        List<DepartmentEmployee> allEmployeeDepartments = query.list();

        session.close();

        return allEmployeeDepartments;
    }
}
