package com.scaziti.learningapi.controllers;

import com.scaziti.learningapi.exception.ResourceNotFoundException;
import com.scaziti.learningapi.model.Cat;
import com.scaziti.learningapi.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping
    public ResponseEntity<List<Cat>> getAll(){
        return ResponseEntity.ok(catRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getById(@PathVariable Integer id){
        return catRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cat createCat(@RequestBody Cat cat){
        return catRepository.save(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable int id, @RequestBody Cat catDetails){

        Cat updateCat = catRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cat not exist with id: " + id));

        updateCat.setName(catDetails.getName());
        updateCat.setSex(catDetails.getSex());
        updateCat.setColor(catDetails.getColor());
        updateCat.setBreed(catDetails.getBreed());
        updateCat.setBirthDate(catDetails.getBirthDate());

        catRepository.save(updateCat);

        return ResponseEntity.ok(updateCat);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        catRepository.deleteById(id);
    }
}
