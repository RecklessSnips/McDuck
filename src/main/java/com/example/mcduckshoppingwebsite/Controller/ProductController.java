package com.example.mcduckshoppingwebsite.Controller;

import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/searchProduct")
    public ResponseEntity<List<String>> searchProduct(@RequestBody String keyword){
        List<String> results = productService.searchProduct(keyword);
        return results != null
                ? new ResponseEntity<>(results, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/searchProductByCategory")
    public ResponseEntity<List<Product>> searchProductByCategory(@RequestBody String keyword){
        List<Product> products = productService.searchProductByCategory(keyword);
        return products != null
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/addProduct")
    public ResponseEntity<Boolean> addProduct(@RequestBody Product product){
        System.out.println("Controller: " + product);
        boolean result = productService.addProduct(product);
        return result
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/addBatchProduct")
    public ResponseEntity<Boolean> addBatchProduct(@RequestBody List<Product> products){
        return productService.addBatchProduct(products)
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    /*
    用于主页默认展示页面的product，每次 mount Home Component，将会调用这个 endpoint，重新获取随机的商品
     */
    @GetMapping("/api/getRandomProduct")
    public ResponseEntity<List<Product>> getRandomProducts(){
        return new ResponseEntity<>(productService.getRandomProducts(), HttpStatus.OK);
    }

    /*  用Filter过滤所选择的产品种类   */

    @PostMapping("/api/getCategory")
    public ResponseEntity<List<Product>> getCategory(@RequestBody String category){
        System.out.println("Category: " + category);
        return new ResponseEntity<>(productService.getCategory(category.replace("\"", "")), HttpStatus.OK);
    }

    /*
    Romance
    Science
    Star Wars
    Thriller
     */
    @GetMapping("/api/getRomanceBooks")
    public ResponseEntity<List<Product>> getRomanceBooks(){
        return new ResponseEntity<>(productService.getRomanceBooks(), HttpStatus.OK);
    }

    @GetMapping("/api/getScienceBooks")
    public ResponseEntity<List<Product>> getScienceBooks(){
        return new ResponseEntity<>(productService.getScienceBooks(), HttpStatus.OK);
    }

    @GetMapping("/api/getStarWarsBooks")
    public ResponseEntity<List<Product>> getStarWarsBooks(){
        return new ResponseEntity<>(productService.getStarWarsBooks(), HttpStatus.OK);
    }

    @GetMapping("/api/getThrillerBooks")
    public ResponseEntity<List<Product>> getThrillerBooks(){
        return new ResponseEntity<>(productService.getThrillerBooks(), HttpStatus.OK);
    }

    /*
    Computer
    Headset
    Speaker
    TV
     */
    @GetMapping("/api/getComputers")
    public ResponseEntity<List<Product>> getComputers(){
        return new ResponseEntity<>(productService.getComputers(), HttpStatus.OK);
    }

    @GetMapping("/api/getHeadsets")
    public ResponseEntity<List<Product>> getHeadsets(){
        return new ResponseEntity<>(productService.getHeadsets(), HttpStatus.OK);
    }

    @GetMapping("/api/getSpeakers")
    public ResponseEntity<List<Product>> getSpeakers(){
        return new ResponseEntity<>(productService.getSpeakers(), HttpStatus.OK);
    }

    @GetMapping("/api/getTVs")
    public ResponseEntity<List<Product>> getTVs(){
        return new ResponseEntity<>(productService.getTVs(), HttpStatus.OK);
    }

    /*
    Men   Clothes
    Women Clothes
     */
    @GetMapping("/api/getMenClothes")
    public ResponseEntity<List<Product>> getMenClothes(){
        return new ResponseEntity<>(productService.getMenClothes(), HttpStatus.OK);
    }

    @GetMapping("/api/getWomenClothes")
    public ResponseEntity<List<Product>> getWomenClothes(){
        return new ResponseEntity<>(productService.getWomenClothes(), HttpStatus.OK);
    }
    /*
    Refrigerator
    CoffeeMaker
    AirFryer
     */
    @GetMapping("/api/getRefrigerator")
    public ResponseEntity<List<Product>> getRefrigerator(){
        return new ResponseEntity<>(productService.getRefrigerator(), HttpStatus.OK);
    }

    @GetMapping("/api/getCoffeeMaker")
    public ResponseEntity<List<Product>> getCoffeeMaker(){
        return new ResponseEntity<>(productService.getCoffeeMaker(), HttpStatus.OK);
    }

    @GetMapping("/api/getAirFryer")
    public ResponseEntity<List<Product>> getAirFryer(){
        return new ResponseEntity<>(productService.getAirFryer(), HttpStatus.OK);
    }
    /* Sports:
        Outdoor
     */
    @GetMapping("/api/getOutdoor")
    public ResponseEntity<List<Product>> getOutdoor(){
        return new ResponseEntity<>(productService.getOutdoor(), HttpStatus.OK);
    }
}
