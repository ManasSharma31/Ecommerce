package com.manas.order_service.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.manas.order_service.Exception.BusinessException;

import jakarta.ws.rs.core.MediaType;

import java.util.*;

@Service
public class ProductClient {

    private static final Logger LOGGER=LoggerFactory.getLogger(ProductClient.class);
    private final RestTemplate restTemplate;
    ProductClient(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @Value("${application.config.product-url}")
    String productUrl;


        public List<ProductPurchaseReponse> getProducts(List<ProductPurchaseRequest>reqs){
            LOGGER.info("Calling product service to get products");
            HttpHeaders headers=new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON);
            HttpEntity<List<ProductPurchaseRequest>>requestEntity=new HttpEntity<>(reqs,headers);
            ResponseEntity<List<ProductPurchaseReponse>>responseEntity=restTemplate.exchange(
                productUrl+"/purchase",
                HttpMethod.POST,
                requestEntity, //in case of GET request this will be null.
                new ParameterizedTypeReference<List<ProductPurchaseReponse>>() {});   
        
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("Error from product service");
        }
        return responseEntity.getBody();
    }

}
