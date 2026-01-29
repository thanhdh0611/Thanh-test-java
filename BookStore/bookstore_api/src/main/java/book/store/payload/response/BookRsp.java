package book.store.payload.response;


import book.store.entity.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRsp {

    private int id;
    private String name;
    private double price;
    private String image;
    private String desc;
    private float rating;

    private String author;
}
