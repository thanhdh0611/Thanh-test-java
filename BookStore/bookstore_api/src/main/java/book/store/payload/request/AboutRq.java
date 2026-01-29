package book.store.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutRq {
    private int age;
    private String degree;
    private int usersId;
    private String email;
    private String phone;
}
