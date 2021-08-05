package com.example.demo.repository;


import com.example.demo.model.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authorities, Integer> {
    Authorities findByAuthorityName(String name);
}
