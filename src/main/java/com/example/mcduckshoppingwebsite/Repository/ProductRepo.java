package com.example.mcduckshoppingwebsite.Repository;

import com.example.mcduckshoppingwebsite.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> searchProduct(String keyword){
        String sql = "SELECT * FROM Product WHERE category LIKE ? LIMIT 8";
        StringBuilder builder = new StringBuilder();
        for(char c: keyword.replaceAll(" ", "").toCharArray()){
            builder.append("%").append(c);
        }
        builder.append("%");
        System.out.println("Keywords: " + builder);
        List<String> name = jdbcTemplate.query(sql, new Object[]{builder.toString()}, (rs, rowNum) -> rs.getString("product_name"));
        for(String s: name){
            System.out.println("Searched name: " + s);
        }
        System.out.println("-----------------------------------------------------------------------");
        return name;
    }

    public boolean addProduct(Product product){
        String sql = "INSERT INTO Product (\n" +
                "    product_id ,\n" +
                "    category,\n" +
                "    product_name,\n" +
                "    author,\n" +
                "    description,\n" +
                "    price,\n" +
                "    stock_quantity,\n" +
                "    review_star,\n" +
                "    review_message,\n" +
                "    image_path\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String generatedProductId = UUID.randomUUID().toString().substring(0, 6);
        System.out.println("Generated Product ID: " + generatedProductId);
        int rows = jdbcTemplate.update(sql, generatedProductId,
                product.getCategory(), product.getProduct_name(), product.getAuthor(),
                product.getDescription(), product.getPrice(), product.getStock_quantity(),
                product.getReview_star(), product.getReview_message(), product.getImage_path());
        System.out.println("Rows affected: " + rows);
        return rows != 0;
    }

    public boolean addBatchProduct(List<Product> products){
        String sql = "INSERT INTO Product (\n" +
                "    product_id ,\n" +
                "    category,\n" +
                "    product_name,\n" +
                "    author,\n" +
                "    description,\n" +
                "    price,\n" +
                "    stock_quantity,\n" +
                "    review_star,\n" +
                "    review_message,\n" +
                "    image_path\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int totalRows = 0;
        for (Product product: products){
            int rows = jdbcTemplate.update(sql, UUID.randomUUID().toString().substring(0, 6),
                    product.getCategory(), product.getProduct_name(), product.getAuthor(),
                    product.getDescription(), product.getPrice(), product.getStock_quantity(),
                    product.getReview_star(), product.getReview_message(), product.getImage_path());
            totalRows += rows;
        }
        System.out.println("Total inserted rows: " + totalRows);
        return totalRows == products.size();
    }

    public List<Product> getRandomProducts(){
        String sql = "SELECT * FROM Product ORDER BY RAND() LIMIT 20";
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        for(Product product: products){
            System.out.println("Retrieved products: " + product.getProduct_name());
        }
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        return products;
    }
}
