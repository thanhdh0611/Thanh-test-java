package book.store.payload.request;

import book.store.entity.Publisher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class BookRq {

    @NotNull(message = "File cannot be left blank")
    private MultipartFile file;

    @NotBlank(message = "Name cannot be left blank")
    private String name;

    @DecimalMin(value = "0.1")
    private double price;

    private String desc;

    private int quantity;

    private int categoryId;

    private float rating;

    private Publisher publisherId;

}
