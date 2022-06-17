package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRespository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRespository;

	@Override
	public void addCustomer(CustomerDTO customerDto) throws InfyBankException {
		Optional<Customer> optional = customerRespository.findById(customerDto.getCustomerId());
		if (optional.isPresent())
			throw new InfyBankException("Service.CUSTOMER_FOUND");
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setEmailId(customerDto.getEmailId());
		customer.setName(customerDto.getName());
		customer.setCustomerId(customerDto.getCustomerId());
		customerRespository.save(customer);
	}

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRespository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setDateOfBirth(customer.getDateOfBirth());
		customerDto.setEmailId(customer.getEmailId());
		customerDto.setName(customer.getName());
		return customerDto;
	}

	@Override
	public List<CustomerDTO> findAll() throws InfyBankException {
		Iterable<Customer> customers = customerRespository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO customerDto = new CustomerDTO();
			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setDateOfBirth(customer.getDateOfBirth());
			customerDto.setEmailId(customer.getEmailId());
			customerDto.setName(customer.getName());
			customerDTOs.add(customerDto);
		});
		if (customerDTOs.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");

		return customerDTOs;
	}

}
