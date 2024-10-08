package com.example.mcduckshoppingwebsite.Repository;

import com.example.mcduckshoppingwebsite.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<String> searchProduct(String keyword){
//        String sql = "SELECT * FROM Product WHERE category LIKE ? OR product_name LIKE ? LIMIT 8";
//
//        StringBuilder builder = new StringBuilder();
//        for(char c: keyword.replaceAll(" ", "").toCharArray()){
//            builder.append("%").append(c);
//        }
//        builder.append("%");
//        System.out.println("Keywords: " + builder);
//        // 填补两个 ？，都用同样的pattern来搜索
//        Object[] searchPattern = new Object[]{builder.toString(), builder.toString()};
//        List<String> name = jdbcTemplate.query(sql, searchPattern, (rs, rowNum) -> rs.getString("product_name"));
//        for(String s: name){
//            System.out.println("Searched name: " + s);
//        }
//        System.out.println("-----------------------------------------------------------------------");
//        return name;
//    }

    public List<Product> searchProductByCategory(String keyword){
        String sql = "SELECT * FROM Product WHERE category LIKE ? OR product_name LIKE ? LIMIT 20";

        // 如果输入为null （暂时没用）
        if(keyword.isEmpty()){
            List<Product> products = getRandomProducts();
            System.out.println("Search pattern is null, return random products");
            return products;
        }else{
            StringBuilder builder = new StringBuilder();
            System.out.println(keyword);
            builder.append("%");
            builder.append(keyword.replaceAll(" ", "%"));
            builder.append("%");
            System.out.println(builder.toString());
            Object[] searchPattern = new Object[]{builder.toString(), builder.toString()};
            List<Product> products = jdbcTemplate.query(sql, searchPattern,
                    (rs, rowNum) -> new Product(
                            rs.getString("product_id"),
                            rs.getString("category"),
                            rs.getString("product_name"),
                            rs.getString("author"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getInt("review_star"),
                            rs.getString("review_message"),
                            rs.getString("image_path"),
                            rs.getTimestamp("listing_date").toLocalDateTime()
                    ));
            System.out.println("M_37: Searched products: " + products);
            return products;
        }
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

    public List<Product> getCategory(String category){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        String searchPattern = "%" + category + "%";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{searchPattern},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        System.out.println(products.size());
        return products;
    }

    // 拿取书
    public List<Product> getRomanceBooks(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%romance%"},
            (rs, rowNum) -> new Product(
                    rs.getString("product_id"),
                    rs.getString("category"),
                    rs.getString("product_name"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity"),
                    rs.getInt("review_star"),
                    rs.getString("review_message"),
                    rs.getString("image_path"),
                    rs.getTimestamp("listing_date").toLocalDateTime()
            ));
        return products;
    }

    public List<Product> getScienceBooks(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%science%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getStarWarsBooks(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%starwars%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getThrillerBooks(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%thriller%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    // 拿取电子产品
    public List<Product> getComputers(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%computer%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getHeadsets(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%headset%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getSpeakers(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%speaker%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getTVs(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%TV%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    // 时尚
    public List<Product> getMenClothes(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"MenClothes%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getWomenClothes(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"WomenClothes%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    // Kitchen
    public List<Product> getRefrigerator(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%Refrigerator%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getCoffeeMaker(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%CoffeeMaker%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getAirFryer(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%AirFryer%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }

    public List<Product> getOutdoor(){
        String sql = "SELECT * FROM Product WHERE category LIKE ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{"%Outdoor%"},
                (rs, rowNum) -> new Product(
                        rs.getString("product_id"),
                        rs.getString("category"),
                        rs.getString("product_name"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("review_star"),
                        rs.getString("review_message"),
                        rs.getString("image_path"),
                        rs.getTimestamp("listing_date").toLocalDateTime()
                ));
        return products;
    }
}
