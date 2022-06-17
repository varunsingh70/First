package com.infy.service;

import java.util.List;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

public interface CustomerService {
	public void addCustomer(CustomerDTO customer) throws InfyBankException;
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;
	public List<CustomerDTO> findAll() throws InfyBankException;
}
