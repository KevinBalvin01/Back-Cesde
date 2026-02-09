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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pangea/v1/products")
public class controllerProduct {

    @Autowired
    ServiceProduct service;

    @PostMapping
    @Operation(summary = "Service to register a product in the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200",description = "registered product",
            content = @Content(schema = @Schema(implementation = Product.class))
        ),
        @ApiResponse(responseCode = "400",description = "error with the registration",
            content = @Content(mediaType = "application/json", 
            examples = @ExampleObject(value = """
                    {"message":"check the entered fields"}
                    """)))
    })
    public ResponseEntity<Product> saveProductController(@RequestBody Product datos) {
        Product respuesta = this.service.saveProduct(datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<Product>> searchAllProductsController() {
        List<Product> lista = this.service.searchProduct();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductbyIdController(@PathVariable Integer id) {
        this.service.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}