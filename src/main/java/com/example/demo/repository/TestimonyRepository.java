/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Testimony;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Uduak-pc
 */
@Repository
public interface TestimonyRepository extends CrudRepository<Testimony, Long>{
    List<Testimony> findByApproved(int value);
    Testimony findBySubject(String value);
    List<Testimony> findByEmail(String value);
    List<Testimony> findByPhone(String value);
    List<Testimony> findByFirstName(String value);
    List<Testimony> findByLastName(String value);
}
