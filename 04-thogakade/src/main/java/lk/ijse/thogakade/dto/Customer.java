package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/21/23 - 9:22 AM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
    private Double salary;
}
