package com.employee.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.employee.config.AesDcrypt;
import com.employee.entity.Employee;


@RestController
@RequestMapping("/Client")
public class CilentRestController {
	
	@Autowired
	RestTemplate rest;
	
	@GetMapping("/employee/details/{id}")
	public Employee getemployeedetails(@PathVariable long id) {
		Employee result=rest.getForObject("https://localhost:9292/Employeedetails/fetch/"+id, Employee.class);
		result.setDOB(AesDcrypt.decrypt(result.getDOB()));
		return result;
	}


}
