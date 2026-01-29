package book.store.controller;

import book.store.payload.request.BookRq;
import book.store.payload.response.BaseRsp;
import book.store.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Value("${root.file.path}")
    private String rootPath;

    @Autowired
    private IBookService iBookService;

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


    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@Valid BookRq bookRq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        String filename = bookRq.getFile().getOriginalFilename();
        BaseRsp baseRsp = new BaseRsp();
        try {
            Path pathRoot = Paths.get(rootPath);
            if (!Files.exists(pathRoot)) {
                Files.createDirectory(pathRoot);
            }
            Files.copy(bookRq.getFile().getInputStream(), pathRoot.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
//            iBookService.addBook(bookRq);
            baseRsp.setData(iBookService.addBook(bookRq));
        } catch (IOException e) {
            // Xử lý lỗi ở đây
            return new ResponseEntity<>("Error uploading the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(baseRsp, HttpStatus.OK);
    }


    @GetMapping("/getAllBook")
    public ResponseEntity<?> getAllBook() {
        iBookService.getAllBook();
        return new ResponseEntity<>(iBookService.getAllBook(), HttpStatus.OK);
    }


    @PostMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookRq bookRq) {
        if (!iBookService.exist(id)){
            return  new ResponseEntity<>("Sản phẩm không tồn tại",HttpStatus.NOT_FOUND);
        }
        iBookService.updateBook(id,bookRq);
        return new ResponseEntity<>("Sách đã được cập nhật",HttpStatus.OK);
    }

    @GetMapping("/getBookDetail")
    public ResponseEntity<?> getBookDetail(@RequestParam("id") int id) {
        iBookService.getBookDetail(id);
        return new ResponseEntity<>(iBookService.getBookDetail(id), HttpStatus.OK);
    }

}
