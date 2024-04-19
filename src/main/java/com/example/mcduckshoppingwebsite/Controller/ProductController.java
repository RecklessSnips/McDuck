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
}
