package book.store.service.impl;

import book.store.entity.Author;
import book.store.entity.Book;
import book.store.entity.BookAuthor;
import book.store.payload.request.BookRq;
import book.store.payload.response.BookRsp;
import book.store.repository.AuthorRepository;
import book.store.repository.BookAuthorRepository;
import book.store.repository.BookRepository;
import book.store.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {


    @Value("${host.name}")
    private String hostName;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public boolean addBook(BookRq bookRq) {
        try {
            Book book = new Book();
            book.setName(bookRq.getName());
            book.setPrice(bookRq.getPrice());
            book.setImage(bookRq.getFile().getOriginalFilename());
            book.setQuantity(bookRq.getQuantity());
            book.setDesc(bookRq.getDesc());
            book.setRating(bookRq.getRating());
            book.setPublisher(bookRq.getPublisherId());
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<BookRsp> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        List<BookRsp> bookRsps = new ArrayList<>();
        for (Book book : bookList) {
            //Lấy ID của book entity
            int idBook = book.getId();
            //Tìm BookAuthor bằng idBook
            BookAuthor bookAuthor = bookAuthorRepository.findBookAuthorByBook_Id(idBook);
            //đổi từ Optional thành đối tượng
//            BookAuthor bookAuthor1 = bookAuthorOptional.get();

//            bookAuthor1.getAuthor().getAuthorName();
//            Author author1 = authorRepository.findById(bookAuthor1.getAuthor().getId()).get();

            BookRsp bookRsp = new BookRsp();
            bookRsp.setId(book.getId());
            bookRsp.setName(book.getName());
            bookRsp.setRating(book.getRating());
            bookRsp.setPrice(book.getPrice());
//            bookRsp.setImage(hostName + "/" + book.getImage());
            bookRsp.setImage(book.getImage());
            bookRsp.setAuthor(bookAuthor.getAuthor().getAuthorName());
            bookRsps.add(bookRsp);
        }
        return bookRsps;
    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public void updateBook(int id, BookRq bookRq) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setName(bookRq.getName());
            book.setPrice(bookRq.getPrice());
            book.setDesc(bookRq.getDesc());
            bookRepository.save(book);
        }
    }

    @Override
    public BookRsp getBookDetail(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        BookRsp bookRsp = new BookRsp();
        if (book != null) {

            bookRsp.setId(book.getId());
            bookRsp.setPrice(book.getPrice());
            bookRsp.setImage(book.getImage());
            bookRsp.setName(book.getName());
            bookRsp.setDesc(book.getDesc());
            bookRsp.setRating(book.getRating());
            return bookRsp;
        }
        return null;
    }
}
