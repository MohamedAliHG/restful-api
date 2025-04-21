package gl2.example.personnel.controller;



import gl2.example.personnel.model.Employee;
import gl2.example.personnel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }
    @GetMapping("/position/{position}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeesByPosition(@PathVariable String position){return employeeService.getEmployeesByPosition(position);}

    @GetMapping("/salary/{salary}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeesWithSalary(@PathVariable Double salary) {return employeeService.getEmployeesWithSalary(salary);}
    
    @GetMapping("/salarybetween/{salary1}/{salary2}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeesWithSalaryBetween(@PathVariable Double salary1,@PathVariable Double salary2) {return employeeService.getEmployeesWithSalaryBetween(salary1,salary2);}

    @GetMapping("/salarygreaterthan/{salary}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeesWithSalaryGreaterThan(@PathVariable Double salary) {return employeeService.getEmployeesWithSalaryGreaterThan(salary);}

    @GetMapping("/salarylessthan/{salary}")
    @PreAuthorize("hasAuthority('SCOPE_readpersonnel')")
    public List<Employee> getEmployeesWithSalaryLessThan(@PathVariable Double salary) {return employeeService.getEmployeesWithSalaryLessThan(salary);}


    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_addpersonnel')")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_deletepersonnel')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_updatepersonnel')")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        // Optionnel : vérifier si l'employé avec l'ID donné existe
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour l'employé avec les nouvelles informations
        employee.setId(id);  // S'assurer que l'ID de l'objet est le même que celui dans l'URL
        Employee updatedEmployee = employeeService.addEmployee(employee);

        return ResponseEntity.ok(updatedEmployee);
    }
}


