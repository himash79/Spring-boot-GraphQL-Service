package lk.himash.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import lk.himash.config.GraphQLConfigure;
import lk.himash.dto.CustomerDto;
import lk.himash.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerGraphQLController {

    private final CustomerService customerService;

    @GetMapping("/getAllCustomersGQL")
    public ResponseEntity<Object> getAll(@RequestBody String query) {
        ExecutionResult result = GraphQLConfigure.graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping("/saveCustomerGQL")
    public ResponseEntity<Object> saveCustomer(@RequestBody String query) {
        ExecutionResult result = GraphQLConfigure.graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
