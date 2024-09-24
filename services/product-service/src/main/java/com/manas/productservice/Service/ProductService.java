package com.manas.productservice.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.manas.productservice.Entity.Product;
import com.manas.productservice.Exception.ProductException;
import com.manas.productservice.Exception.ProductNotFoundException;
import com.manas.productservice.Model.ProductPurchaseReponse;
import com.manas.productservice.Model.ProductPurchaseRequest;
import com.manas.productservice.Model.ProductRequest;
import com.manas.productservice.Model.ProductResponse;
import com.manas.productservice.Repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;


    public Integer createProduct(ProductRequest productRequest){
        Product product=mapper.map(productRequest,Product.class);
        return productRepository.save(product).getId();
    }


    public List<ProductPurchaseReponse> purchase(List<ProductPurchaseRequest> requests) {
       List<Integer>reuqestedProductIds=requests.stream().map(ProductPurchaseRequest :: productId).collect(Collectors.toList());
       List<Product> products=productRepository.findAllByIdInOrderById(reuqestedProductIds);
       if(reuqestedProductIds.size()!=products.size()) {
        throw new ProductException("Not all requested products found");
       }

       //List<ProductPurchaseRequest>sortedPurchaseRequests=requests.stream().sorted((a,b)->a.id()-b.id()).toList();
       List<ProductPurchaseRequest>sortedPurchaseRequests=requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

       List<ProductPurchaseReponse> responseList=new ArrayList<>();
       for(int i=0;i<sortedPurchaseRequests.size();i++) {
            ProductPurchaseRequest request=sortedPurchaseRequests.get(i);
            Product product=products.get(i);
            if(request.quantity()>product.getQuantity()) {
                throw new ProductException("Not enough quantity of product with id "+product.getId());
            }
            product.setQuantity(product.getQuantity()-request.quantity());
            productRepository.save(product);
            ProductPurchaseReponse response=new ProductPurchaseReponse(product.getId(),product.getName(),product.getDescription(),product.getPrice(),request.quantity());
            responseList.add(response);
       }
       return responseList;
    }


    public ProductResponse getProductById(Integer productId) {
       return productRepository.findById(productId)
       .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getQuantity(),product.getPrice(), product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription()))
       .orElseThrow(()->new ProductNotFoundException("Product with "+productId+" not found"));
    }


    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(
            product ->new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getQuantity(),product.getPrice(), product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription())).toList();
    }
}
