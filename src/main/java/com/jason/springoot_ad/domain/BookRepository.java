package com.jason.springoot_ad.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  BookRepository extends JpaRepository<Book,Long> {

}
