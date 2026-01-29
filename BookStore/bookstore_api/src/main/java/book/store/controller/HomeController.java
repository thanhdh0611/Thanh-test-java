package book.store.controller;

import book.store.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/hello")
public class HomeController {

    @Value("${root.file.path}")
    private String rootPath;

    @Autowired
    private JWTHelperUtils jwtHelperUtils;

    @GetMapping("")
    public ResponseEntity<?> hello() {
        String token = jwtHelperUtils.generateToken("huy12345");
        return new ResponseEntity<>(jwtHelperUtils.validateToken(token), HttpStatus.OK);
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<?> downloadFileProduct(@PathVariable String filename) throws FileNotFoundException {
        try {
            // Định nghĩa đường dẫn folder lưu file
            Path path = Paths.get(rootPath);
            System.out.println(path);
            // Định nghĩa đường dẫn tới file được lưu.
            Path pathFile = path.resolve(filename);
            System.out.println(pathFile);
            Resource resource = new UrlResource(pathFile.toUri());
            if (resource.exists() || resource.isReadable()) {
                // Cho phép download file
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                throw new FileNotFoundException("Không tìm thấy file");
            }
        } catch (Exception e) {
            // Lỗi
            throw new FileNotFoundException("Không thể tìm thấy file");
        }
    }

    @GetMapping("/testAuthentication")
    public ResponseEntity<?> testAuthentication(Authentication authentication) {
//        System.out.println(JWTHelperUtils.generateSecretKey());
        System.out.println(authentication.toString());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        String token = jwtHelperUtils.generateToken("Huydeptrai");
        String data = jwtHelperUtils.validateToken(token);

        return new ResponseEntity<>(authentication.toString(), HttpStatus.OK);
    }
}
