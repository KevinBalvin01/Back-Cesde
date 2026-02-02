package com.pangea.pangea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangea.pangea.models.Product;
@Repository
public interface IProduct extends JpaRepository<Product, Integer> {

}
