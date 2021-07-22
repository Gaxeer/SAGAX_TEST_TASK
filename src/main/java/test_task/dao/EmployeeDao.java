package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(
            value = "select e.id, e.name, e.salary, e.boss_id, e.department_id " +
                    "from employee e, employee b " +
                    "where e.boss_id = b.id AND e.salary > b.salary",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "select e.id, e.name, e.salary, e.boss_id, e.department_id " +
                    "from employee e, (select max(salary) as max_sal, department_id " +
                    "from employee group by department_id) as m " +
                    "where e.salary = m.max_sal AND e.department_id = m.department_id",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "select e.id, e.name, e.boss_id, e.department_id " +
                    "from employee e, employee d " +
                    "where e.boss_id = d.id AND e.department_id != d.department_id",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
