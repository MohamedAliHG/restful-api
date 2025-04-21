package gl2.example.personnel.repository;

import gl2.example.personnel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   public List<Employee> findByName(String name);
   public List<Employee> findByPosition(String position);
   public List<Employee> findBySalaryGreaterThan(double salary);
   public List<Employee> findBySalaryLessThan(double salary);
   public List<Employee> findBySalaryBetween(double salary1, double salary2);
   public List<Employee> findBySalary(double salary);

}
