package lk.himash.controller;

import lk.himash.dto.CustomerDto;
import lk.himash.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<Object> getCustomers() {
        return customerService.getCustomers();
    }

}
