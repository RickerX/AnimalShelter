package com.example.animalshelter.repository;

import com.example.animalshelter.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface GuardianRepository extends JpaRepository<Guardian,Long> {
    Optional<Guardian> findGuardianByUsername(String username);
    @Query(value = "select * from guardian where chat_id is not null", nativeQuery = true)
    Collection<Guardian> findAllWithExistingChatId();
}
