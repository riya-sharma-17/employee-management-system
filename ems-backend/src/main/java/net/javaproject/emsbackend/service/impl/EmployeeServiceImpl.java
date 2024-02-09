package net.javaproject.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaproject.emsbackend.dto.EmployeeDto;
import net.javaproject.emsbackend.entity.Employee;
import net.javaproject.emsbackend.exception.ResourceNotFoundException;
import net.javaproject.emsbackend.mapper.EmployeeMapper;
import net.javaproject.emsbackend.repository.DepartmentRepository;
import net.javaproject.emsbackend.repository.EmployeeRepository;
import net.javaproject.emsbackend.service.EmployeeService;
import org.springframework.stereotype.Service;
import net.javaproject.emsbackend.entity.Department;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department is not exists with id: " + employeeDto.getDepartmentId()));

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Employee does not exist with given id :"+employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List <Employee> employees=employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exist with given id: "+employeeId)
        );

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Department department = departmentRepository.findById(updateEmployee.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department is not exists with id: " + updateEmployee.getDepartmentId()));

        employee.setDepartment(department);

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exist with given id: "+employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }
}

