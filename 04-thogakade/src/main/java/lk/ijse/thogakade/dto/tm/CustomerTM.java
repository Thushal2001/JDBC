package lk.ijse.thogakade.dto.tm;

/*
    @author DanujaV
    @created 3/21/23 - 9:42 AM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private Double salary;
}
