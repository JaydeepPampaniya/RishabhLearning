package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public String addProduct(Product product, MultipartFile image) throws IOException {
            Optional<Product> productOptional = productRepository.findByDeviceName(product.getDeviceName());
            if (productOptional.isPresent()) {
                throw new ConflictException(MessageConstants.PRODUCT_ALREADY_EXISTS);
            } else {
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(image.getBytes());
                productRepository.save(product);
                return "Device Added";
            }
    }

    public List<Product> findAllProducts() {
        List<Product> product = productRepository.findAll();
        if(!product.isEmpty()){
            return product;
        }else{
            throw new ConflictException(MessageConstants.PRODUCT_ALREADY_EXISTS);
        }
    }

    public byte[] getImage(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return product.getImageData();
        }else{
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }

    public String updateProduct(Product product, MultipartFile image) throws IOException {
        Optional<Product> productOptional = productRepository.findByDeviceName(product.getDeviceName());
        if(productOptional.isPresent()){
            product.setImageName(image.getOriginalFilename());
            product.setImageType(image.getContentType());
            product.setImageData(image.getBytes());
            productRepository.save(product);
            return "Device details updated";
        }else{
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }

    public Product getProduct(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return productOptional.get();
        }else{
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }

    public String deleteProduct(String deviceName) {
        Optional<Product> product = productRepository.findByDeviceName(deviceName);
        if(product.isPresent()){
            Product product1 = product.get();
            productRepository.delete(product1);
            return "Device Deleted.";
        }else{
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }
}
