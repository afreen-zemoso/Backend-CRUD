package com.example.demo.employee.service;

import com.example.demo.employee.dto.CreateEmployeeDTO;
import com.example.demo.employee.dto.EmployeeDTO;
import com.example.demo.employee.entity.Employee;
import com.example.demo.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

//    public EmployeeDTO saveEmployee(CreateEmployeeDTO createEmployeeDTO) {
//        Employee employee = new Employee();
//        employee.setFirstName(createEmployeeDTO.getFirstName());
//        employee.setLastName(createEmployeeDTO.getLastName());
//        employee.setEmail(createEmployeeDTO.getEmail());
//        employee.setPosition(createEmployeeDTO.getPosition());
//        employee.setSalary(createEmployeeDTO.getSalary());
//        Employee savedEmployee = employeeRepository.save(employee);
//        return convertToDTO(savedEmployee);
//    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    public EmployeeDTO saveEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(createEmployeeDTO.getFirstName());
        employee.setEmail(createEmployeeDTO.getEmail());
        employee.setSalary(createEmployeeDTO.getSalary());
        employee.setPosition(createEmployeeDTO.getPosition());
        employee.setLastName(createEmployeeDTO.getLastName());
        employeeRepository.save(employee);
        return convertToDTO(employee);
    }
}
