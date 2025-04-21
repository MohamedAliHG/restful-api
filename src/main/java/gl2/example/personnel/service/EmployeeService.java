package gl2.example.personnel.service;


import gl2.example.personnel.model.Employee;
import gl2.example.personnel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getEmployeesByName(String name) {return employeeRepository.findByName(name);}
    public List<Employee> getEmployeesByPosition(String position) {return employeeRepository.findByPosition(position);}
    public List<Employee> getEmployeesWithSalary(double salary) {return employeeRepository.findBySalary(salary);}
    public List<Employee> getEmployeesWithSalaryGreaterThan(double salary) {return employeeRepository.findBySalaryGreaterThan(salary);}
    public List<Employee> getEmployeesWithSalaryLessThan(double salary) {return employeeRepository.findBySalaryLessThan(salary);}
    public List<Employee> getEmployeesWithSalaryBetween(double salary1,double salary2) {return employeeRepository.findBySalaryBetween(salary1,salary2);}


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
