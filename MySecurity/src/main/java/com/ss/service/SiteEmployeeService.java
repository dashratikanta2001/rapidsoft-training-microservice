package com.ss.service;

import com.ss.dto.FilterDto;
import com.ss.dto.SiteEmployeeDto;
import com.ss.response.Response;

public interface SiteEmployeeService {

	Response<?> createSiteEmployee(SiteEmployeeDto siteEmployeeDto);

	Response<?> getAllSiteEmployeeDetails(Integer companyId);

	Response<?> getAll(FilterDto filterDto);

}
