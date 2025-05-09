package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.DTO.AddToCartDTO;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Cart;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.CartRepository;
import com.PhoneX.Backend.repository.ProductRepository;
import com.PhoneX.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository,UserRepository userRepository,ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    public String addToCart(AddToCartDTO addToCartDTO) {
       Optional<User> userOptional =  userRepository.findById(addToCartDTO.getUserId());
       Optional<Product> productOptional = productRepository.findById(addToCartDTO.getProductId());
       if(userOptional.isEmpty()){
           throw new NotFoundException(MessageConstants.USER_NOT_FOUND);
       }else if(productOptional.isEmpty()){
           throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
       }else{
           Cart cart = new Cart();
           cart.setUser(userOptional.get());
           cart.setProducts(productOptional.get());
           cart.setQuantity(addToCartDTO.getQuantity());
           cartRepository.save(cart);
           return "Product added in cart";
       }
    }

    public boolean checkProductInCart(long id,long productId) {
        Optional<Cart> cartOptional = cartRepository.findByUserIdAndProductId(id,productId);
        return cartOptional.isPresent();
    }

    public String delete(long productId,long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Optional<Cart> cartOptional = cartRepository.findByUserIdAndProductId(id, productId);
            if (cartOptional.isPresent()) {
                Cart cart=cartOptional.get();
                cartRepository.delete(cart);
                return "Product removed from cart.";
            }else{
                throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
            }
        } else {
            throw new NotFoundException(MessageConstants.USER_NOT_FOUND);
        }
    }

    public List<Cart> getProductsInCart(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Optional<List<Cart>> cart = cartRepository.findByUserId(id);
            if(cart.isPresent())
                return cart.get();
            else
                throw new NotFoundException(MessageConstants.CART_IS_EMPTY);
        } else {
            throw new NotFoundException(MessageConstants.USER_NOT_FOUND);
        }
    }
}
