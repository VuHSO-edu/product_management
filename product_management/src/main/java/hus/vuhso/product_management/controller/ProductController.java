package hus.vuhso.product_management.controller;

import hus.vuhso.product_management.dto.request.ProductCreateRequest;
import hus.vuhso.product_management.dto.request.ProductGetRequest;
import hus.vuhso.product_management.dto.response.ProductDeleteResponse;
import hus.vuhso.product_management.dto.response.ProductResponse;
import hus.vuhso.product_management.service.ProductService;
import hus.vuhso.product_management.util.EntryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EntryService.PRODUCT)
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("create")
    public ProductResponse create(@RequestBody ProductCreateRequest request) {
        return productService.create(request);
    }

    @PostMapping("get")
    public ProductResponse get(@RequestBody ProductGetRequest request) {
        return productService.get(request);
    }

    @PostMapping("delete")
    public ProductDeleteResponse delete(@RequestBody ProductGetRequest request) {
        return productService.delete(request);
    }

}
