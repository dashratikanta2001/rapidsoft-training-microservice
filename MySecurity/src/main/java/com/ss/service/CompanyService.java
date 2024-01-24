package com.ss.service;

import com.ss.dto.CompanyDto;
import com.ss.response.Response;

public interface CompanyService {

	Response<?> createCompany(CompanyDto companyDto);

}
