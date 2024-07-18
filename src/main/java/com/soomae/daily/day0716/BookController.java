package com.soomae.daily.day0716;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();
    private Long nextid = 1L; // 강제 설정

    @GetMapping("/books")
    public String list(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        book.setId(nextid++);
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable int id, Model model) {
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found with id: " + id);
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book updatedBook) {
        updateBook(updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
        } else {
            System.out.println("Book not found with id: " + id);
        }
        return "redirect:/books";
    }

    private Book findBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                break;
            }
        }
    }
}



