package book.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name ="about")
@AllArgsConstructor
@NoArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="email")
    private String email;

    @Column(name ="degree")
    private String degree;

    @Column(name ="age")
    private int age;

    @Column(name ="phone")
    private String phone;

    @Column(name ="description")
    private String desc;

    @Column(name = "image")
    private String image;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
