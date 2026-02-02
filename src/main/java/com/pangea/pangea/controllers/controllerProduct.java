package com.pangea.pangea.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangea.pangea.models.Product;
import com.pangea.pangea.services.ServiceProduct;

@RestController
@RequestMapping("/pangea/v1/products")
public class controllerProduct {

    @Autowired
    ServiceProduct service;

    @PostMapping
    public ResponseEntity<Product> guardar(@RequestBody Product datos) {
        Product respuesta = this.service.saveProduct(datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<Product>> buscarTodos() {
        List<Product> lista = this.service.searchProduct();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.service.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}