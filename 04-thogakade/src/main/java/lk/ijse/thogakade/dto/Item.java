package lk.ijse.thogakade.dto;

/*
    @author DanujaV
    @created 3/14/23 - 3:57 PM   
*/

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String code;
    private String description;
//    private double unitPrice;
    private Double unitPrice;
    private Integer qtyOnHand;
}
