package book.store.service;

import book.store.payload.request.BookRq;
import book.store.payload.response.BookRsp;

import java.util.List;

public interface IBookService {

    boolean addBook(BookRq bookRq);

    List<BookRsp> getAllBook();

    boolean exist(int id);
    void updateBook(int id,BookRq bookRq);


    BookRsp getBookDetail(int id);



}
