package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.DTO.AddProductDTO;
import com.PhoneX.Backend.DTO.UpdateProductDto;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Categories;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.CategoriesRepository;
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
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoriesRepository categoriesRepository){
        this.productRepository = productRepository;
        this.categoriesRepository=categoriesRepository;
    }

    public String addProduct(AddProductDTO productDTO, MultipartFile image) throws IOException {
            Optional<Product> productOptional = productRepository.findByDeviceName(productDTO.getDeviceName());
            Optional<Categories> categoriesOptional=categoriesRepository.findById(productDTO.getCategories());
            if (productOptional.isPresent()) {
                throw new ConflictException(MessageConstants.PRODUCT_ALREADY_EXISTS);
            } else {
                if(categoriesOptional.isPresent()){
                    Product product=new Product();
                    product.setImageName(image.getOriginalFilename());
                    product.setImageType(image.getContentType());
                    product.setImageData(image.getBytes());
                    product.setCompanyName(productDTO.getCompanyName());
                    product.setDeviceName(productDTO.getDeviceName());
                    product.setCurrentPrice(productDTO.getCurrentPrice());
                    product.setOriginalPrice(productDTO.getOriginalPrice());
                    product.setDiscount(productDTO.getDiscount());
                    product.setDescription(productDTO.getDescription());
                    product.setCategories(categoriesOptional.get());
                    productRepository.save(product);
                    return "Device Added";
                }else{
                    throw  new NotFoundException("Categories not found");
                }
            }
    }

    public List<Product> findAllProducts() {
        List<Product> product = productRepository.findAll();
        if(!product.isEmpty()){
            return product;
        }else{
            throw new ConflictException(MessageConstants.PRODUCT_NOT_FOUND);
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

    public String updateProduct(UpdateProductDto updateProductDto, MultipartFile image) throws IOException {
        Optional<Product> productsOptional = productRepository.findByDeviceName(updateProductDto.getDeviceName());
        if (productsOptional.isPresent()) {
            Product product = productsOptional.get();
            Optional<Categories> categoriesOptional = categoriesRepository.findById(updateProductDto.getCategories());
            if (categoriesOptional.isPresent()) {
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(image.getBytes());
                product.setCompanyName(updateProductDto.getCompanyName());
                product.setDeviceName(updateProductDto.getDeviceName());
                product.setCurrentPrice(updateProductDto.getCurrentPrice());
                product.setOriginalPrice(updateProductDto.getOriginalPrice());
                product.setDiscount(updateProductDto.getDiscount());
                product.setDescription(updateProductDto.getDescription());
                product.setCategories(categoriesOptional.get());
                productRepository.save(product);
                return "Device details updated";
            } else {
                throw new NotFoundException("Categories not found");
            }
        } else{
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

    public String deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "Device Deleted.";
        }else{
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }
}
