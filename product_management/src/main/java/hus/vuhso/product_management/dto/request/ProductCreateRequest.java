package hus.vuhso.product_management.dto.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String name;
    private String description;
    private Double price;
}
