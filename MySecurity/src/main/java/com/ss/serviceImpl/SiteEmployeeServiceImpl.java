package com.ss.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ss.dto.FilterDto;
import com.ss.dto.SiteDataDto;
import com.ss.dto.SiteEmployeeDto;
import com.ss.entity.SiteEmployee;
import com.ss.repository.SiteEmployeeRepository;
import com.ss.response.Response;
import com.ss.service.SiteEmployeeService;



@Service
public class SiteEmployeeServiceImpl implements SiteEmployeeService {

	@Autowired
	private SiteEmployeeRepository siteEmployeeRepository;

	@Override
	public Response<?> createSiteEmployee(SiteEmployeeDto siteEmployeeDto) {

		if (siteEmployeeDto != null) {

			SiteEmployee siteEmployee = SiteEmployeeDto.convertToEntity(siteEmployeeDto);

			SiteEmployee employee = siteEmployeeRepository.save(siteEmployee);

			if (employee != null) {
				return new Response<>("SiteEmployee Save", SiteEmployeeDto.convertToDto(employee),
						HttpStatus.OK.value());
			}

			else {
				return new Response<>("SiteEmployee not Save", SiteEmployeeDto.convertToDto(employee),
						HttpStatus.OK.value());

			}

		}
		return new Response<>("Provide Valid Site Details", null, HttpStatus.BAD_REQUEST.value());

	}

	@Override
	public Response<?> getAllSiteEmployeeDetails(Integer companyId) {
	

			List<SiteEmployee> siteEmp = siteEmployeeRepository.getAllSiteEmployeeByCompanyId(companyId);
			if (siteEmp != null) {
				List<SiteEmployeeDto> siteemployees = siteEmp.stream().filter(e -> e.getIsActive()).map(SiteEmployeeDto::convertToDto)
						.collect(Collectors.toList());
				return new Response<>(" Site Employee Details", siteemployees, HttpStatus.OK.value());

			}

			else {
				return new Response<>("No Data", null, HttpStatus.BAD_REQUEST.value());

			}
	
	}
	
	@Override
	public Response<?> getAll (FilterDto filterDto){
		
		List<SiteEmployee> siteEmp = siteEmployeeRepository.getAllSiteEmployeeByCompanyId(filterDto.getCompanyId());
		
		List<SiteDataDto> list = siteEmp.stream().map(e->new SiteDataDto(e.getName(),e.getIsActive())).collect(Collectors.toList());

		
		if(list!=null && !list.isEmpty()) {
			return new Response<>("Site List",list,HttpStatus.OK.value());

		}
	
		return new Response<>("Site List not Found",null,HttpStatus.BAD_REQUEST.value());
	}
	
	

}
