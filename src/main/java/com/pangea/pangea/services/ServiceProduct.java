package com.pangea.pangea.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pangea.pangea.models.Product;
import com.pangea.pangea.repositories.IProduct;

@Service
public class ServiceProduct {
    @Autowired
    private IProduct repositoryProduct;
    //Create 
    public Product saveProduct(Product data){
        if (data.getStock() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "The stock can't be 0");
        }
        return this.repositoryProduct.save(data);
    }
    //Read
    public List<Product> searchProduct(){
        return this.repositoryProduct.findAll();
    }
    //Delete 
    public void deleteProductById(Integer id){
        Optional<Product> serchProductById = this.repositoryProduct.findById(id);
        if (!serchProductById.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Doesn't exist"
            );
        }
        this.repositoryProduct.deleteById(id);
    }


}
