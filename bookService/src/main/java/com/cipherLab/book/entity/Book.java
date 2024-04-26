package com.cipherLab.book.entity;

import com.cipherLab.book.dto.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    private @Id @GeneratedValue Long id;
    private String name;
    private String genre;
    private String cellNumber;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private LocalDateTime lastChangeTs;
}
