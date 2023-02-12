package lk.himash.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
@ToString
public class Customer {

    @Id
    private String id;
    private String email;
    private String nic;
}
