package com.soomae.daily.day0717;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private List<Book> books = new ArrayList<>();
  private Long nextId = 1L;

  @GetMapping // 모든 도서 조회
  public List<Book> getAllBooks() {
    return books;
  }

  @GetMapping("/{id}") // 특정 도서 조회
  public Book getBookById(@PathVariable("id") Long id) {
    return books.stream()
        .filter(b -> b.getId() == id)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("해당 도서는 존재하지 않습니다!"));
  }

  @PostMapping // 새 도서 등록
  public Book createBook(@RequestBody Book book) {
    book.setId(nextId++);
    books.add(book);
    return book;
  }

  @PutMapping("/{id}") // 도서 정보 조회
  public Book updateBook(@PathVariable("id") Long id, @RequestBody Book updateBook) {
    Book book =
        books.stream()
            .filter(b -> b.getId() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 도서는 존재하지 않습니다!"));

    book.setTitle(updateBook.getTitle());
    book.setAuthor(updateBook.getAuthor());
    return book;
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable("id") Long id) {
    books.removeIf(b -> b.getId() == id);
  }
}
