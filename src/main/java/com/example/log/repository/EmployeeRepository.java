package com.example.log.repository;

import com.example.log.Employee.Employee;
import com.example.log.dto.EmployeeDto;
import com.example.log.dto.ReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT SUM (e.salary)FROM Employee e")
    int getSumOfSalaries();

    @Query("SELECT AVG (e.salary)FROM Employee e")
    double getAverageOfSalaries();

    @Query("SELECT new com.example.workfille.dto.EmployeeDto(e.id,e.name,e.salary,p.position)FROM Employee e LEFT JOIN FETCH position p WHERE e.salary=(SELECT MIN (e.salary)FROM Employee e)")
    Page<EmployeeDto> getEmployeeWithMinSalary(Pageable pageable);

    @Query("SELECT new com.example.workfille.dto.EmployeeDto(e.id,e.name,e.salary,p.position)FROM Employee e LEFT JOIN FETCH position p WHERE e.salary=(SELECT MAX(e.salary)FROM Employee e)")
    List<EmployeeDto> getEmployeeWithMaxSalary();

    List<Employee> findEmployeeBySalaryIsGreaterThan(double salary);

    List<Employee> findEmployeeByPosition_Position(String position);

    @Query("SELECT new ru.skypro.lessons.workfille.ReportDto(e.position.position,count(e.id),max (e.salary),min (e.salary),avg (e.salary), FROM Employee e GROUP BY e.position.position")
    List<ReportDto>buildReport();

}
