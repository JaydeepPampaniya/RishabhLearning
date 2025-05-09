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
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;


    @Transactional
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

    @Transactional
    public boolean checkProductInCart(long id,long productId) {
        User user= userRepository.findById(id).orElseThrow(()-> new NotFoundException(MessageConstants.USER_NOT_FOUND));
        Product products = productRepository.findById(productId).orElseThrow(()-> new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND));
        Optional<Cart> cartOptional = cartRepository.findByUserAndProducts(user,products);
        return cartOptional.isPresent();
    }

    @Transactional
    public String delete(long productId,long id) {
        User user= userRepository.findById(id).orElseThrow(()-> new NotFoundException(MessageConstants.USER_NOT_FOUND));
        Product products = productRepository.findById(productId).orElseThrow(()-> new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND));
        Cart cart = cartRepository.findByUserAndProducts(user, products).orElseThrow(()-> new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND));
        cartRepository.delete(cart);
        return "Product removed from cart.";

    }

    @Transactional
    public List<Cart> getProductsInCart(long id) {
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException(MessageConstants.USER_NOT_FOUND));
        return cartRepository.findByUser(user).orElseThrow(()->new NotFoundException(MessageConstants.CART_IS_EMPTY));
    }
}
