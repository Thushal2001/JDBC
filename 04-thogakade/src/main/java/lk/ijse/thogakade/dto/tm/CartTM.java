package lk.ijse.thogakade.dto.tm;

/*
    @author DanujaV
    @created 3/21/23 - 11:45 AM   
*/

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private String code;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Button btn;
}
