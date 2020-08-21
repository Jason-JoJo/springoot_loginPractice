package com.jason.springoot_ad.domain.service;

import com.jason.springoot_ad.domain.Book;
import com.jason.springoot_ad.domain.BookRepository;
import com.jason.springoot_ad.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        // Book book = bookRepository.findOne(id); 出錯改為下面的(spring Boot 版本問題)
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new BookNotFoundException("Book not excite");
        }
        return book;
    }
}
