package ca.jrf.employee_app.web;

import ca.jrf.employee_app.data.models.Employee;
import ca.jrf.employee_app.data.payloads.request.EmployeeRequest;
import ca.jrf.employee_app.data.payloads.response.MessageResponse;
import ca.jrf.employee_app.service.EmployeeService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@ApiResponses(value={
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),

})
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Integer id) {
        Employee employee = employeeService.getASingleEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addEmployee(@RequestBody EmployeeRequest employee) {
        MessageResponse newEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public Optional<Employee> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employee) {
        return employeeService.updateEmployee(id, employee);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
