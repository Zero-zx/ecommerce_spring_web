package com.example.shopping.controller;

import com.example.shopping.models.Comment;
import com.example.shopping.models.Product;
import com.example.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String productPage(@RequestParam(required = false) String query, Model model) {
        List<Product> products = productService.getAllProducts();
        if (query != null && !query.isEmpty()) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                    .toList();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/{productId}")
    public String productDetailPage(@PathVariable String productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @PostMapping("/add-comment")
    public String addComment(@RequestParam String productId, @RequestParam String user, @RequestParam String text, @RequestParam int rating) {
        Comment comment = new Comment(user, text, rating);
        productService.addCommentToProduct(productId, comment);
        return "redirect:/product/" + productId;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String productId) {
        productService.addToCart(productId);
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", productService.getCartItems());
        return "cart";
    }
}
