package in.hefshine.agromart.service;

import org.springframework.stereotype.Service;
import in.hefshine.agromart.dto.Cart;
import in.hefshine.agromart.dto.CartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private Map<Long, Cart> cartMap = new HashMap<>();

    public void addToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartMap.getOrDefault(userId, new Cart());
        
        // Ensure the items list is initialized
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>()); // Initialize the list if it's null
        }

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        cart.getItems().add(cartItem); 
        cartMap.put(userId, cart);
    }

    public Cart getCart(Long userId) {
        return cartMap.get(userId);
    }

    public void createBill(Long userId, Cart cart) {
        System.out.println("Bill generated for user: " + userId);
        System.out.println("Products: " + cart.getItems());
    }

}

