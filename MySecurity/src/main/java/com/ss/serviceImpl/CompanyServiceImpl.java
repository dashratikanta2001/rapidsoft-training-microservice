package com.ss.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ss.dto.CompanyDto;
import com.ss.entity.Company;
import com.ss.repository.CompanyRepository;
import com.ss.response.Response;
import com.ss.service.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Response<?> createCompany(CompanyDto companyDto) {
		
		if(companyDto!= null) {
			
			Company company = CompanyDto.toCompany(companyDto);
			
			Company saveCompany= companyRepository.save(company);
			
			if(saveCompany!=null) {
				return new Response<>("Company Added",CompanyDto.toCompanyDto(saveCompany),HttpStatus.OK.value());
			}
			
			else {
				return new Response<>("Company  not Added",null,HttpStatus.BAD_REQUEST.value());

			}
		}
		return null;
	}

}
