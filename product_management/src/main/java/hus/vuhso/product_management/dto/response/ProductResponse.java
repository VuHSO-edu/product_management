package hus.vuhso.product_management.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductResponse {
    private Integer status;
    private String message;
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
