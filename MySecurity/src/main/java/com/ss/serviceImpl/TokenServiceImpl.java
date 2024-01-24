package com.ss.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.dao.CrediantialDao;
import com.ss.dao.EmployeeDao;
import com.ss.entity.CredentialMaster;
import com.ss.entity.Employee;
import com.ss.response.JwtRequest;
import com.ss.response.JwtResponse;
import com.ss.security.JwtHelper;
import com.ss.service.CredenstialService;
import com.ss.service.TokenService;


@Service
public class TokenServiceImpl  implements TokenService{
	
	
	@Autowired
	public EmployeeDao employeeDao;
	
	@Autowired
	private CustumUserDetalisService custumUserDetalisService;


	@Autowired
	private JwtHelper jwthelper;

	@Autowired
	private CrediantialDao credentialDao;

	@Autowired
	private CredenstialService credentialService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public JwtResponse generateToken(JwtRequest jwtRequest) throws Exception {
		
		CredentialMaster master = credentialDao.getByUsername(jwtRequest.getUsername());

		CredentialMaster userentity = new CredentialMaster();

		if (master != null) {

//			if (userentity.passwordMatcher(jwtRequest.getPassword(), master.getPassword())) {
				if (passwordEncoder.matches(jwtRequest.getPassword(), master.getPassword())) {
					
				

				JwtResponse jwtResponse = new JwtResponse();
				UserDetails userDetalis = custumUserDetalisService.loadUserByUsername(jwtRequest.getUsername());
				String token = this.jwthelper.generateToken(userDetalis);
				System.out.println("tokan -" + token);
				String name = this.jwthelper.getUsernameFromToken(token);

				Employee employee = employeeDao.getEmployeeByPnone(jwtRequest.getUsername());

				jwtResponse.setId(employee.getId());

				// jwtResponse.setName(employee.getFirstname() +" " +employee.getLastname());
				jwtResponse.setToken(token);

				return jwtResponse;

			} else {
				throw new Exception("Employee Not Found!!!");
			}

		} else {
			throw new Exception("Employee Not Found!!!");
		}

	}

}
