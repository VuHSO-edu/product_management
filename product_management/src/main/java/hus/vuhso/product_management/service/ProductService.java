package hus.vuhso.product_management.service;

import hus.vuhso.product_management.dto.request.ProductCreateRequest;
import hus.vuhso.product_management.dto.request.ProductGetRequest;
import hus.vuhso.product_management.dto.response.ProductDeleteResponse;
import hus.vuhso.product_management.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse create(ProductCreateRequest request);
    ProductResponse get(ProductGetRequest request);
    ProductDeleteResponse delete(ProductGetRequest request);
}
