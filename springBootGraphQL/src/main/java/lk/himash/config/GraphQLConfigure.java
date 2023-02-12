package lk.himash.config;

import lk.himash.dto.CustomerDto;
import lk.himash.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class GraphQLConfigure {

    @Value("classpath:customer.graphqls")
    private Resource schemaResource;

    public static GraphQL graphQL;

    private final CustomerService customerService;

    @Bean
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<ResponseEntity<Object>> getAllCustomers = data -> {
            return (ResponseEntity<Object>) customerService.getCustomers();
        };

        DataFetcher<ResponseEntity<Object>> saveCustomer = data -> {
            CustomerDto customerDto = new CustomerDto(data.getArgument("id"),data.getArgument("email"),data.getArgument("nic"));
            return customerService.saveCustomer(customerDto);
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting ->
                                typeWriting.dataFetcher("getAllCustomers", getAllCustomers)
                                           .dataFetcher("saveCustomer", saveCustomer)
                )
                .build();

    }

}
