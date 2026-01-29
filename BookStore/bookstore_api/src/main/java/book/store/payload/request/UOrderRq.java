package book.store.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UOrderRq {

    private double totalPrice;
    private List<OrderDetailRq> order;

}
