package com.ss.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ss.dao.EmployeeDao;
import com.ss.dto.EmployeeDto;
import com.ss.dto.EmployeePaginateRequestDto;
import com.ss.dto.EmployeelistDto;
import com.ss.dto.PaginateRequestDto;
import com.ss.entity.Company;
import com.ss.entity.Employee;
import com.ss.repository.CompanyRepository;
import com.ss.repository.CredentialMasterRepository;
import com.ss.repository.EmployeeRepository;
import com.ss.response.Response;
import com.ss.service.CredenstialService;
import com.ss.service.DesignationService;
import com.ss.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeDao employeeDao;
	
	@Autowired
	private CompanyRepository companyRepository;
	
//	@Autowired
//	private DesignationServiceImpl designationService;


	@Autowired
	private CredenstialService credentialService;

	@Autowired
	public CredentialMasterRepository credentialMasterRepositoy;

	public Response<?> createEmployee(EmployeeDto employeeDto) {

//		if (employeeDto != null) {
//
			Employee employee = EmployeeDto.toEmployee(employeeDto);
//
//			if (employee == null) {
//				System.out.println("Employee = null");
//			}
			
//
//				Employee save = employeeDao.createEmployee(employee);
			
//			employee.setCompany(companyRepository.findById(employeeDto.getCompany().getId()).get());
//			employee.setDesignation(designationService.);

				 Employee savedEmployee = employeeRepository.save(employee);

				 System.out.println(savedEmployee.toString());
//			}
//			return new Response<>("Employee Not Found", null, HttpStatus.BAD_REQUEST.value());
//		}

		return credentialService.adduserInCrediantial(employeeDto);
	}

	@Override
	public Response<?> getAllEmp(Integer companyId) {

		List<Employee> employee = employeeRepository.getAllEmployeeByCompanyId(companyId);

		// List<Employee> employee =
		// employeeDao.getAll(filterDto.getCompanyId(),filterDto.getName(),filterDto.getEmpCode());
		// List<Employee> employee = employeeDao.getAllEmployee(filterDto);

		if (employee != null && !employee.isEmpty()) {
			List<EmployeelistDto> employees = employee.stream().filter(e -> e.getIsActive())
					.map(e -> new EmployeelistDto(e.getId(), e.getFirstname() + " " + e.getLastname(), e.getEmpCode(),
							e.getDepartment().getName(), e.getDesignation().getName(), e.getPhone(), e.getEmail(),
							e.getCompany().getId()))
					.collect(Collectors.toList());
			return new Response<>("All employees", employees, HttpStatus.OK.value());

		}

		else {
			return new Response<>("Employees Not fetched", null, HttpStatus.BAD_REQUEST.value());

		}
	}

//
	@Override
	public Response<?> getAll(String search) {

		// List<Employee> employee =
		// employeeRepository.getAllEmployeeByCompanyId(companyId);

		List<Employee> employee = employeeRepository.search(search);

		List<EmployeelistDto> employeelist = new ArrayList<>();

		employee.stream().filter(Employee::getIsActive).forEach(x -> {
			try {
				employeelist.add(EmployeelistDto.toConvertDto(x));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return new Response<>("All employees", employeelist, HttpStatus.OK.value());

	}

	@Override
	public EmployeePaginateRequestDto getAllemployees(PaginateRequestDto paginationRequest) {

		Page<Employee> employees = employeeDao.getAllEmployees(paginationRequest);

		List<EmployeeDto> employeess = employees.getContent().stream().map(EmployeeDto::toDto)
				.collect(Collectors.toList());

		EmployeePaginateRequestDto obj = new EmployeePaginateRequestDto(employees.getSize(),
				employees.getTotalElements(), employees.getTotalPages(), employeess);

		return obj;

	}

//	public JwtResponse generateToken(JwtRequest jwtRequest) throws Exception {
//
//		System.out.println("Inside Generate Tokan");
//
//		CredentialMaster master = userDao.getUsername(jwtRequest.getUsername());
//
//		CredentialMaster userentity = new CredentialMaster();
//
//		if (master != null) {
//
//			if (userentity.passwordMatcher(jwtRequest.getPassword(), master.getPassword())) {
//
//				JwtResponse jwtResponse = new JwtResponse();
//				UserDetails userDetalis = custumUserDetalisService.loadUserByUsername(jwtRequest.getUsername());
//				String token = this.jwtUtil.generateToken(userDetalis);
//				System.out.println("tokan -" + token);
//				String name = this.jwtUtil.getUsernameFromToken(token);
//
//				Employee employee = employeeDao.getEmployeeByPnone(jwtRequest.getUsername());
//
//				jwtResponse.setId(employee.getId());
//
//				// jwtResponse.setName(employee.getFirstname() +" " +employee.getLastname());
//				jwtResponse.setToken(token);
//
//				return jwtResponse;
//
//			} else {
//				throw new Exception("User Not Found!!!");
//			}
//
//		} else {
//			throw new Exception("User Not Found!!!");
//		}
//
//	}
}

//	public JwtResponse checkStudentAndGenerateToken(JwtRequest jwtRequest) throws Exception {
//
//		Employee employee = employeeDao.getEmployeeByPnone(jwtRequest.getUsername());
//		System.out.println(jwtRequest.getUsername());
//
//		if (employee != null) {
//			if (employee.getPassword() != null) {
//				if (passwordEncoder.matches(jwtRequest.getPassword(), employee.getPassword())) {
//					UserDetails userDetalis = this.customUserDetailsService
//							.loadUserByUsername(jwtRequest.getUsername());
//					String token = this.jwtUtil.generateToken(userDetalis);
//					System.out.println("tokan -" + token);
//					return new JwtResponse(token);
//				} else {
//					throw new CustomeException("Invalid Credential!!!");
//				}
//			} else {
//				throw new CustomeException("Invalid Credential!!!");
//			}
//		} else {
//			throw new CustomeException("Invalid Credential!!!");
//		}
//
//	}
