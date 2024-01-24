package com.ss.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ss.dao.CrediantialDao;
import com.ss.dao.EmployeeDao;
import com.ss.dto.EmployeeDto;
import com.ss.entity.CredentialMaster;
import com.ss.entity.Employee;
import com.ss.response.Response;
import com.ss.service.CredenstialService;

@Service
public class CredentialServiceImpl implements CredenstialService {

	@Autowired
	private CrediantialDao crediantialDao;

	@Autowired
	private EmployeeDao employeeDao;

	final public String password = "1234";

	@Override
	public Response<?> adduserInCrediantial(EmployeeDto employeeDto) {

		Employee employee = employeeDao.getEmployeeByPnone(employeeDto.getPhone());

		CredentialMaster credentialMaster = new CredentialMaster();

		credentialMaster.setEmployee(employee);
		System.out.println("User name : "+employee.getEmpCode());
		credentialMaster.setUsername(employee.getEmpCode());
		credentialMaster.setupdatedOn(new Date());
		credentialMaster.setIsActive(true);
		credentialMaster.setCreatedOn(new Date());
		credentialMaster.setCreated_by(employeeDto.getFirstname()+""+employeeDto.getLastname());
		credentialMaster.setPassword(credentialMaster.passwordEncoder(password));


		Integer save = crediantialDao.saveCrediantial(credentialMaster);

		return new Response<>("Employee Succesfully Added", employeeDto, HttpStatus.OK.value());
	}
}











//package com.ss.serviceImpl;
//
//import java.util.Date;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.ss.dao.CrediantialDao;
//import com.ss.dao.EmployeeDao;
//import com.ss.dto.EmployeeDto;
//import com.ss.entity.CredentialMaster;
//import com.ss.entity.Employee;
//import com.ss.response.Response;
//import com.ss.service.CredenstialService;
//
//@Service
//@Transactional
//public class CredentialServiceImpl implements CredenstialService {
//
//	@Autowired
//	private CrediantialDao crediantialDao;
//
//	@Autowired
//	private EmployeeDao employeeDao;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	final public String password = "1234";
//
//	@Override
//	public Response<?> adduserInCrediantial(EmployeeDto employeeDto) {
//
//		Employee employee = employeeDao.getEmployeeByPnone(employeeDto.getPhone());
//
//		CredentialMaster credentialMaster = new CredentialMaster();
//
//		credentialMaster.setEmployee(employee);
//		System.out.println("User name : "+employee.getEmpCode());
//		credentialMaster.setUsername(employee.getEmpCode());
//		credentialMaster.setupdatedOn(new Date());
//		credentialMaster.setIsActive(true);
//		credentialMaster.setCreatedOn(new Date());
//		credentialMaster.setCreated_by(employeeDto.getFirstname()+""+employeeDto.getLastname());
//		credentialMaster.setPassword(passwordEncoder.encode(password));
//
//		Integer save = crediantialDao.save(credentialMaster).getId();
//
//		return new Response<>("Employee Succesfully Added", employeeDto, HttpStatus.OK.value());
//	}
//}
