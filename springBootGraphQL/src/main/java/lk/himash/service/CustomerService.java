package lk.himash.service;

import lk.himash.dto.CustomerDto;
import lk.himash.model.Customer;
import lk.himash.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepo;

    public ResponseEntity<Object> saveCustomer(CustomerDto cusDto) {
        Customer cus = new Customer();
        BeanUtils.copyProperties(cusDto, cus);
        customerRepo.save(cus);
        return new ResponseEntity<>(cus, HttpStatus.OK);
    }

    public ResponseEntity<Object> getCustomers() {
        List<Customer> cus = new ArrayList<>();
        cus = customerRepo.findAll();
        ResponseEntity<Object> res = new ResponseEntity<>(cus, HttpStatus.OK);
        log.info(String.valueOf(res.getHeaders()));
        log.info(String.valueOf(res.getBody()));
        return new ResponseEntity<>(cus, HttpStatus.OK);
    }

}
