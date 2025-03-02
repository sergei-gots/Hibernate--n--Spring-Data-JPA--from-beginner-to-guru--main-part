package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookDao {

    Book getById(Long id);

    List<Book> findAll();

    List<Book> findAll(int limit, int offset);

    List<Book> findAll(Pageable pageable);

    default List<Book> findAllSortByTitle(Pageable pageable) {

        Sort.Order titleSortOrderIfExist = pageable.getSort().getOrderFor("title");
        Sort.Direction titleSortOrderDirection = titleSortOrderIfExist != null ?
                titleSortOrderIfExist.getDirection() : Sort.DEFAULT_DIRECTION;


        Pageable pageableSortByTitle = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(titleSortOrderDirection, "title")
        );

        return findAll(pageableSortByTitle);
    }

    Book findBookByTitle(String title);

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);

}
