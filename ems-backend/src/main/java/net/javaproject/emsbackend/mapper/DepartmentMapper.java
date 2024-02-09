package net.javaproject.emsbackend.mapper;

import net.javaproject.emsbackend.dto.DepartmentDto;
import net.javaproject.emsbackend.entity.Department;

public class DepartmentMapper {
    // convert departnment jpa entity into department dto
    public static DepartmentDto mapToDepartmentDto (Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
                );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }

}
