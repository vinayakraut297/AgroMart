package in.hefshine.agromart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.hefshine.agromart.dto.Cart;
import in.hefshine.agromart.dto.CartItem;
import in.hefshine.agromart.dto.MessageResponse;
import in.hefshine.agromart.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<Object> addToCart(@RequestBody CartItem cartItem) {
		cartService.addToCart(cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
		return ResponseEntity.ok(new MessageResponse("Product added to cart"));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
		Cart cart = cartService.getCart(userId);
		return ResponseEntity.ok(cart);
	}

	
	@PostMapping("/checkout")
	public ResponseEntity<MessageResponse> checkout(@RequestBody Cart cart) {
	    cartService.createBill(cart.getUserId(), cart);
	    return ResponseEntity.ok(new MessageResponse("Bill created successfully"));
	}


}
