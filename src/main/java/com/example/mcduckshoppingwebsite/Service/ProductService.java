package com.example.mcduckshoppingwebsite.Service;

import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<String> searchProduct(String keyword){
        return productRepo.searchProduct(keyword);
    }

    public List<Product> searchProductByCategory(String keyword){
        return productRepo.searchProductByCategory(keyword);
    }

    public boolean addProduct(Product product){
        return productRepo.addProduct(product);
    }

    public boolean addBatchProduct(List<Product> products){
        return productRepo.addBatchProduct(products);
    }

    public List<Product> getRandomProducts(){
        return productRepo.getRandomProducts();
    }
}
