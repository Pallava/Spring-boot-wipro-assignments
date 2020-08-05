package com.wipro.pa832670.assignment_4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wipro.pa832670.assignment_4.model.Employee;



@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
