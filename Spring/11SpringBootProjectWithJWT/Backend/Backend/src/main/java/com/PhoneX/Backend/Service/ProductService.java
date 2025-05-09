package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.DTO.AddProductDTO;
import com.PhoneX.Backend.DTO.UpdateProductDto;
import com.PhoneX.Backend.DTO.responseDTO.ProductResponseDTO;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.DeviceType;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.DeviceTypeRepository;
import com.PhoneX.Backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final DeviceTypeRepository deviceTypeRepository;

    private String encodeImage(byte[] imageData) {
        return (imageData != null) ? Base64.getEncoder().encodeToString(imageData) : null;
    }

    @Transactional
    public String addProduct(MultipartFile image, AddProductDTO productDTO) throws IOException {
        Optional<Product> productOptional = productRepository.findByDeviceName(productDTO.getDeviceName());
        Optional<DeviceType> deviceTypeOptional = deviceTypeRepository.findById(productDTO.getDeviceTypeId());
        if (productOptional.isPresent()) {
            throw new ConflictException(MessageConstants.PRODUCT_ALREADY_EXISTS);
        } else {
            if (deviceTypeOptional.isPresent()) {
                Product product = new Product();
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(image.getBytes());
                product.setCompanyName(productDTO.getCompanyName());
                product.setDeviceName(productDTO.getDeviceName());
                product.setCurrentPrice(productDTO.getCurrentPrice());
                product.setOriginalPrice(productDTO.getOriginalPrice());
                product.setDiscount(productDTO.getDiscount());
                product.setDescription(productDTO.getDescription());
                product.setDeviceType(deviceTypeOptional.get());
                productRepository.save(product);
                return "Device Added";
            } else {
                throw new NotFoundException(MessageConstants.DEVICE_TYPE_NOT_FOUND);
            }
        }
    }

    @Transactional
    public Page<ProductResponseDTO> findAllProducts(String deviceType, Pageable pageable) {
        Page<Product> products;
        if (!deviceType.isEmpty()) {
            DeviceType deviceTypeName = deviceTypeRepository.findByName(deviceType).orElseThrow(() -> new NotFoundException(MessageConstants.DEVICE_TYPE_NOT_FOUND));
            products = productRepository.findByDeviceType(deviceTypeName, pageable);
            System.out.println(products);
        } else {
            products = productRepository.findAll(pageable);
        }

        if (products.isEmpty()) {
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }

        return products.map(product -> new ProductResponseDTO(
                product.getId(),
                encodeImage(product.getImageData()),
                product.getCompanyName(),
                product.getDeviceName(),
                product.getOriginalPrice(),
                product.getCurrentPrice(),
                product.getDiscount(),
                product.getDescription(),
                product.getDeviceType()
        ));
    }


    @Transactional
    public String updateProduct(UpdateProductDto updateProductDto, MultipartFile image) throws IOException {
        Optional<Product> productsOptional = productRepository.findByDeviceName(updateProductDto.getDeviceName());
        if (productsOptional.isPresent()) {
            Product product = productsOptional.get();
            Optional<DeviceType> deviceType = deviceTypeRepository.findById(updateProductDto.getDeviceTypeId());
            if (deviceType.isPresent()) {
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(image.getBytes());
                product.setCompanyName(updateProductDto.getCompanyName());
                product.setDeviceName(updateProductDto.getDeviceName());
                product.setCurrentPrice(updateProductDto.getCurrentPrice());
                product.setOriginalPrice(updateProductDto.getOriginalPrice());
                product.setDiscount(updateProductDto.getDiscount());
                product.setDescription(updateProductDto.getDescription());
                product.setDeviceType(deviceType.get());
                productRepository.save(product);
                return "Device details updated";
            } else {
                throw new NotFoundException(MessageConstants.DEVICE_TYPE_NOT_FOUND);
            }
        } else {
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }

    @Transactional
    public ProductResponseDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND));
        return new ProductResponseDTO(
                product.getId(),
                encodeImage(product.getImageData()),
                product.getCompanyName(),
                product.getDeviceName(),
                product.getOriginalPrice(),
                product.getCurrentPrice(),
                product.getDiscount(),
                product.getDescription(),
                product.getDeviceType()
        );

    }

    @Transactional
    public String deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return "Device Deleted.";
        } else {
            throw new NotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        }
    }
}
