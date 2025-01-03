package com.example.shopping.services;

import com.example.shopping.models.CartItem;
import com.example.shopping.models.Comment;
import com.example.shopping.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = List.of(
            new Product("1", "Java Programming 2nd edition", "A book of how to deal with java programming, with real usecase and samples.", 12.99, "/images/book.jpg"),
            new Product("2", "Blue T-shirt", "Shirt of Steven in minecraft. Let your imaginary flies unlimitedly.", 19.99, "/images/shirt.jpg"),
            new Product("3", "Acer Nitro 5 AN14AG-25", "Superior gaming laptop for gamer, ultra-high performance.", 899.99, "/images/laptop.jpg"),
            new Product("4", "Surface Headphones", "Noise-cancelling headphones. Best bluetooth wireless model.", 299.99, "/images/headphone.jpg")
    );

    private final List<CartItem> cart = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public void addToCart(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            // Check if product is already in cart
            CartItem cartItem = cart.stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (cartItem == null) {
                // If not in cart, add new cart item with quantity 1
                cart.add(new CartItem(product, 1));
            } else {
                // If already in cart, increment the quantity
                cartItem.incrementQuantity();
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void updateCartQuantity(String productId, int quantity) {
        cart.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    public void addCommentToProduct(String productId, Comment comment) {
        Product product = getProductById(productId);
        if (product != null) {
            product.addComment(comment);
        }
    }

    public void updateProductRating(String productId, double rating) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setRating(rating);
        }
    }
}

