package com.cipherLab.OrderMicroService.controller;

import com.cipherLab.OrderMicroService.dto.OrderRequestDto;
import com.cipherLab.OrderMicroService.dto.ResponseDto;
import com.cipherLab.OrderMicroService.entity.Order;
import com.cipherLab.OrderMicroService.exception.OrderNotFoundException;
import com.cipherLab.OrderMicroService.repository.OrderRepository;
import com.cipherLab.OrderMicroService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cipherLab.OrderMicroService.constants.OrderConstant.MESSAGE_201;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrder() {
        var orders = orderRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        var order = orderRepository.findById(id);
        if (order.isEmpty()) throw new OrderNotFoundException(id);
        else return ResponseEntity.status(HttpStatus.OK).body(order.get());
    }

//    @PutMapping("/orders/{id}")
//    public ResponseEntity<ResponseDto> updateBookById(@RequestBody Book newBook, @PathVariable Long id) {
//        Optional<Book> bookOptional = orderRepository.findById(id);
//        if (bookOptional.isEmpty()) {
//            throw new BookNotFoundException(id);
//        } else {
//            var oldBook = bookOptional.get();
//            oldBook.setName(newBook.getName() != null ? newBook.getName() : oldBook.getName());
//            oldBook.setGenre(newBook.getGenre() != null ? newBook.getGenre() : oldBook.getGenre());
//            newBook.setLastChangeTs(LocalDateTime.now());
//            orderRepository.save(oldBook);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, com.cipherLab.book.constants.OrderConstant.MESSAGE_200));
//        }
//    }

    @PostMapping("/orders")
    public ResponseEntity<ResponseDto> createBookOrder(@RequestBody OrderRequestDto orderRequestDto) {
        var orderEvent = orderService.createBookOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED, MESSAGE_201));
    }

    @DeleteMapping("/orders/{id}")
    ResponseEntity<ResponseDto> deleteOrder(@PathVariable Long id) {
        var order = orderRepository.findById(id);
        if (order.isEmpty()) throw new com.cipherLab.OrderMicroService.exception.OrderNotFoundException(id);
        else {
            orderRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, com.cipherLab.OrderMicroService.constants.OrderConstant.MESSAGE_200));
        }

    }

}
