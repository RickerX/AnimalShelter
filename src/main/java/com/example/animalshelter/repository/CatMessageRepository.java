package com.example.animalshelter.repository;

import com.example.animalshelter.model.CatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatMessageRepository extends JpaRepository<CatMessage,String> {
}
