package hus.vuhso.product_management.service.impl;

import hus.vuhso.product_management.dto.request.ProductCreateRequest;
import hus.vuhso.product_management.dto.request.ProductGetRequest;
import hus.vuhso.product_management.dto.response.ProductDeleteResponse;
import hus.vuhso.product_management.dto.response.ProductResponse;
import hus.vuhso.product_management.entity.ProductEntity;
import hus.vuhso.product_management.repository.ProductRepository;
import hus.vuhso.product_management.service.ProductService;
import hus.vuhso.product_management.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private RedisTemplate<String, Object> redisTemplate;
    private RabbitTemplate rabbitTemplate;

    @Override
    public ProductResponse create(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse();
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(request.getName());
            productEntity.setDescription(request.getDescription());
            productEntity.setPrice(request.getPrice());
            var save = productRepository.save(productEntity);
            response.setStatus(ErrorCode.PRODUCT_SUCCESS.getStatus());
            response.setMessage(ErrorCode.PRODUCT_SUCCESS.getMessage());
            response.setId(save.getId());
            response.setName(save.getName());
            response.setDescription(save.getDescription());
            response.setPrice(save.getPrice());
            response.setCreatedAt(save.getCreatedAt());
            response.setUpdatedAt(save.getUpdatedAt());

            //Redis
            redisTemplate.opsForValue().set("product_" + save.getId(), save);

            //RabbitMQ
            rabbitTemplate.convertAndSend("product", "New product created with id: " + save.getName());

            return response;
        } catch (Exception e) {
            log.error("Error: ", e);
            response.setStatus(ErrorCode.PRODUCT_CREATE_FAILED.getStatus());
            response.setMessage(ErrorCode.PRODUCT_CREATE_FAILED.getMessage());
            return response;
        }
    }

    @Override
    public ProductResponse get(ProductGetRequest request) {
        ProductResponse response = new ProductResponse();
        try {
            var product = redisTemplate.opsForValue().get("product_" + request.getId());
            if (product != null) {
                response.setStatus(ErrorCode.PRODUCT_SUCCESS.getStatus());
                response.setMessage(ErrorCode.PRODUCT_SUCCESS.getMessage());
                response.setId(((ProductEntity) product).getId());
                response.setName(((ProductEntity) product).getName());
                response.setDescription(((ProductEntity) product).getDescription());
                response.setPrice(((ProductEntity) product).getPrice());
                response.setCreatedAt(((ProductEntity) product).getCreatedAt());
                response.setUpdatedAt(((ProductEntity) product).getUpdatedAt());
            }
            return response;
        } catch (Exception e) {
            log.error("Error: ", e);
            response.setStatus(ErrorCode.PRODUCT_GET_FAILED.getStatus());
            response.setMessage(ErrorCode.PRODUCT_GET_FAILED.getMessage());
            return response;
        }
    }

    @Override
    public ProductDeleteResponse delete(ProductGetRequest request) {
        ProductDeleteResponse response = new ProductDeleteResponse();
        try {
            productRepository.deleteById(request.getId());
            redisTemplate.delete("product_" + request.getId());
            response.setStatus(ErrorCode.PRODUCT_SUCCESS.getStatus());
            response.setMessage(ErrorCode.PRODUCT_SUCCESS.getMessage());
            return response;
        } catch (Exception e) {
            log.error("Error: ", e);
            response.setStatus(ErrorCode.PRODUCT_DELETE_FAILED.getStatus());
            response.setMessage(ErrorCode.PRODUCT_DELETE_FAILED.getMessage());
            return response;
        }
    }
}
