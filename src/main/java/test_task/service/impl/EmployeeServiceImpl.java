package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here

        Long id = 0L;
        for (Employee employee : employees){
            if(employee.getName().equals(name)) {
                employeeDao.delete(employee);
                id = employee.getId();
                break;
            }
        }
        return id;
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here
        Long id = 0L;
        HashSet newEmployees = new HashSet();
        for (Employee employee : employees){
            if(employee.getName().equals(name)) {
                employee.setSalary(new BigDecimal(8888));
                id = employee.getId();
            }
            newEmployees.add(employee);
        }
        employeeDao.saveAll(newEmployees);
        return id;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here
        Long id = 0L;
        Employee newEmployee;
        for (Employee emp:employeeDao.findAll()) {
            if(emp.getDepartment().getId() == 1 && emp.getBoss() == null){
                newEmployee = new Employee(emp.getDepartment(), emp, "Keanu Romero", new BigDecimal(15000));
                employeeDao.save(newEmployee);
                id = newEmployee.getId();
            }
        }
        return id;
    }
}