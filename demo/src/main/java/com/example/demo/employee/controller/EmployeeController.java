package com.example.demo.employee.controller;


import com.example.demo.employee.dto.CreateEmployeeDTO;
import com.example.demo.employee.dto.EmployeeDTO;
import com.example.demo.employee.exception.EmployeeNotFoundException;
import com.example.demo.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
//        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
//        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
        @GetMapping("/{id}")
        public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
            return employeeService.getEmployeeById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
        }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO){
        EmployeeDTO employeeDTO = employeeService.saveEmployee(createEmployeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
