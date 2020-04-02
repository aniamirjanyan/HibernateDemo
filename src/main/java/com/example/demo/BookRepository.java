package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByYear(Long year);
    List<Book> findByTitleAndAuthor(String title, String author);
    //List<Book> findByTitleAndAuthorAndYear(String title, String author, Long year);

    @Query("SELECT b FROM Book b WHERE (:title is null or b.title = :title) and " +
            "(:author is null or b.author = :author) and" +
            "(:year is null or b.author = :year)")
    List<Book> findByTitleAndAuthorAndYear(@Param("title") String title,
                                          @Param("author") String author,
                                          @Param("year") Long year);

}


