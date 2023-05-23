package com.example.animalshelter.repository;

import com.example.animalshelter.model.InfoMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoMessageRepository extends JpaRepository<InfoMessage,String> {
}
