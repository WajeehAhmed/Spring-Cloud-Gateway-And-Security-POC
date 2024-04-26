package com.cipherLab.book.controller;

import com.cipherLab.book.constants.BookConstant;
import com.cipherLab.book.dto.ResponseDto;
import com.cipherLab.book.entity.Book;
import com.cipherLab.book.exception.BookNotFoundException;
import com.cipherLab.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAll() {
        var books = bookRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) throw new BookNotFoundException(id);
        else return ResponseEntity.status(HttpStatus.OK).body(book.get());
    }

    @GetMapping("/books/byCellNumber/{cellNumber}")
    public ResponseEntity<List<Book>> getBookByCellNumber(@PathVariable String cellNumber) {
        var book = bookRepository.findByCellNumber(cellNumber);
        if (book.isEmpty()) throw new BookNotFoundException(cellNumber);
        else return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<ResponseDto> updateBookById(@RequestBody Book newBook, @PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException(id);
        } else {
            var oldBook = bookOptional.get();
            oldBook.setName(newBook.getName() != null ? newBook.getName() : oldBook.getName());
            oldBook.setGenre(newBook.getGenre() != null ? newBook.getGenre() : oldBook.getGenre());
            newBook.setLastChangeTs(LocalDateTime.now());
            bookRepository.save(oldBook);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, BookConstant.MESSAGE_200));
        }
    }

    @PostMapping("/books")
    public ResponseEntity<ResponseDto> createBook(@RequestBody Book newBook) {
        newBook.setLastChangeTs(LocalDateTime.now());
        bookRepository.save(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED, BookConstant.MESSAGE_201));
    }

    @DeleteMapping("/books/{id}")
    ResponseEntity<ResponseDto> deleteBook(@PathVariable Long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) throw new BookNotFoundException(id);
        else {
            bookRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, BookConstant.MESSAGE_200));
        }

    }
}
