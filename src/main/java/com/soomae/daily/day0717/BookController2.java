package com.soomae.daily.day0717;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController2 {

  private List<Book> books = new ArrayList<>();
  private Long nextId = 1L;

  @GetMapping // 모든 도서 조회
  public List<BookDTO> getAllBooks() {
    return books.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @GetMapping("/{id}") // 특정 도서 조회
  public BookDTO getBookById(@PathVariable("id") Long id) {
    Book book =
        books.stream()
            .filter(b -> b.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 도서는 존재하지 않습니다!"));
    return convertToDTO(book);
  }

  @PostMapping // 새 도서 등록
  public BookDTO createBook(@RequestBody BookDTO bookDTO) {
    Book book = convertToEntity(bookDTO);
    book.setId(nextId++);
    books.add(book);
    return convertToDTO(book);
  }

  @PutMapping("/{id}") // 도서 정보 수정
  public BookDTO updateBook(@PathVariable("id") Long id, @RequestBody BookDTO updateBookDTO) {
    Book book =
        books.stream()
            .filter(b -> b.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 도서는 존재하지 않습니다!"));

    book.setTitle(updateBookDTO.getTitle());
    book.setAuthor(updateBookDTO.getAuthor());
    book.setIsbn(updateBookDTO.getIsbn());
    book.setPrice(updateBookDTO.getPrice());
    book.setPublishedYear(updateBookDTO.getPublishedYear());
    return convertToDTO(book);
  }

  @DeleteMapping("/{id}") // 도서 삭제
  public void deleteBook(@PathVariable("id") Long id) {
    boolean removed = books.removeIf(b -> b.getId().equals(id));
    if (!removed) {
      throw new IllegalArgumentException("해당 도서는 존재하지 않습니다!");
    }
  }

  private BookDTO convertToDTO(Book book) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setTitle(book.getTitle());
    bookDTO.setAuthor(book.getAuthor());
    bookDTO.setIsbn(book.getIsbn());
    bookDTO.setPrice(book.getPrice());
    bookDTO.setPublishedYear(book.getPublishedYear());
    return bookDTO;
  }

  private Book convertToEntity(BookDTO bookDTO) {
    Book book = new Book();
    book.setTitle(bookDTO.getTitle());
    book.setAuthor(bookDTO.getAuthor());
    book.setIsbn(bookDTO.getIsbn());
    book.setPrice(bookDTO.getPrice());
    book.setPublishedYear(bookDTO.getPublishedYear());
    return book;
  }
}
