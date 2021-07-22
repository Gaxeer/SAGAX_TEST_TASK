package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(
            value = "select department_id from employee group by department_id having count(id)<=3",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "select department_id " +
                    "from employee " +
                    "group by department_id " +
                    "having(sum(salary) = (select max(total_sal) " +
                    "from (select sum(salary) as total_sal " +
                    "from employee group by department_id) as m))",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
