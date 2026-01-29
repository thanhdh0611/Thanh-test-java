package book.store.repository;

import book.store.entity.Book;
import book.store.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor,Integer> {
    BookAuthor findBookAuthorByBook_Id(int id);
}
