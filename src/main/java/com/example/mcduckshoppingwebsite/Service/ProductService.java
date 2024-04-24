package com.example.mcduckshoppingwebsite.Service;

import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    // 拿取书
    public List<Product> getRomanceBooks(){
        return productRepo.getRomanceBooks();
    }

    public List<Product> getScienceBooks(){
        return productRepo.getScienceBooks();
    }

    public List<Product> getStarWarsBooks(){
        return productRepo.getStarWarsBooks();
    }

    public List<Product> getThrillerBooks(){
        return productRepo.getThrillerBooks();
    }

    // 拿取电子产品
    public List<Product> getComputers(){
        return productRepo.getComputers();
    }

    public List<Product> getHeadsets(){
        return productRepo.getHeadsets();
    }

    public List<Product> getSpeakers(){
        return productRepo.getSpeakers();
    }

    public List<Product> getTVs(){
        return productRepo.getTVs();
    }

    // Fashion
    public List<Product> getMenClothes(){
        return productRepo.getMenClothes();
    }

    public List<Product> getWomenClothes(){
        return productRepo.getWomenClothes();
    }

    // Kitchen
    public List<Product> getRefrigerator(){
        return productRepo.getRefrigerator();
    }

    public List<Product> getCoffeeMaker(){
        return productRepo.getCoffeeMaker();
    }

    public List<Product> getAirFryer(){
        return productRepo.getAirFryer();
    }

}
