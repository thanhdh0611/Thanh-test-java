package book.store.entity.ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsIds implements Serializable {

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "order_id")
    private int orderId;
}
