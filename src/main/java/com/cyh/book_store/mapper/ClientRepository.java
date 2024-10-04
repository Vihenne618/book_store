package com.cyh.book_store.mapper;

import com.cyh.book_store.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}