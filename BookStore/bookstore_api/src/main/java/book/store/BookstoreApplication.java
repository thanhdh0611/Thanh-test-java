package book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {


//	@Autowired
//	private JWTHelperUtils jwtHelperUtils;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);

    }

//	@Bean
//	public String generateSecretKey() {
//		String secretKey = jwtHelperUtils.generateToken("bookstore_api");
//		System.out.println("Token : " + secretKey);
//		return secretKey;
//	}

}
