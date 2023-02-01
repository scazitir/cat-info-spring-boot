package com.scaziti.learningapi.repositories;

import com.scaziti.learningapi.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    public Optional<Cat> findById(Long id);
//    public Optional<Cat> findAllByCatNameContainingIgnoreCase(String catName);
}
