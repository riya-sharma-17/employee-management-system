package net.javaproject.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaproject.emsbackend.dto.DepartmentDto;
import net.javaproject.emsbackend.entity.Department;
import net.javaproject.emsbackend.exception.ResourceNotFoundException;
import net.javaproject.emsbackend.mapper.DepartmentMapper;
import net.javaproject.emsbackend.repository.DepartmentRepository;
import net.javaproject.emsbackend.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department= DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
       Department department= departmentRepository.findById(departmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Department does not exist with given id: " +departmentId)
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
       List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exist with given Id: "+departmentId)
        );

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Department does not exist with id: "+departmentId)
        );

        departmentRepository.deleteById(departmentId);
    }
}
