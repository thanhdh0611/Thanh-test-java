package book.store.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTHelperUtils {

    @Value("${jwt.token.key}")
    private String key;


    // Tạo generateSecretKey. Tạo xong thì bỏ đi.
    public static String generateSecretKey() {
        // Tạo một khóa ngẫu nhiên với mật mã của HS.256
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // Dùng n để tạo ra một khóa bí mat
        String keyBase64 = Encoders.BASE64.encode(secretKey.getEncoded());
        return keyBase64;
    }


    public String generateToken(String data) {

        // Lấy secret key đã tạo ra sử dụng.
        // Decoders.BASE64.decode(key) hascode nó về Byte[] rồi từ byte tạo SecretKey
        //SecretKey này là của thư viện
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        // Dùng key để tạo ra token. Sét các qui định cho token ở đây.Thời gian hết hạn ...v...v
        return Jwts
                .builder()
                .setSubject(data)
                .signWith(secretKey)
                .compact();
    }


    // JWT set gi thì phía dưới get cái đó .
    public String validateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
