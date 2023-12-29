package com.vatsal.com.equity.alert.service;

import com.vatsal.com.equity.alert.Models.EquityCMP;
import com.vatsal.com.equity.alert.repository.EquityCMPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EquityCMPService {

    @Autowired
    private EquityCMPRepository equityCMPRepository;

    public List<EquityCMP> saveEquityCMPData(List<EquityCMP> equityCMPData){
        System.out.println(equityCMPData.toString());
        return equityCMPRepository.saveAll(equityCMPData);
    }

//    public List<Product> saveProducts(List<Product> products){
//        return productRepository.saveAll(products);
//    }
//
//    public Product getProduct(int id){
//        return productRepository.findById(id).orElse(null);
//    }
//
//    public Product getProductByName(String name){
//        return productRepository.findByName(name);
//    }
//
    public List<EquityCMP> getEquityCMPData(){
        return equityCMPRepository.findAll();
    }
//
//    public String deleteProduct(int id){
//        productRepository.deleteById(id);
//        return "Object deleted";
//    }
//
//    public Product updateProduct(Product product){
//        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setQuantity(product.getQuantity());
//
//        return productRepository.save(existingProduct);
//    }


}