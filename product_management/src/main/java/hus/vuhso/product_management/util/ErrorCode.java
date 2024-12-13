package hus.vuhso.product_management.util;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(404, "Product not found"),
    PRODUCT_ALREADY_EXISTS(400, "Product already exists"),
    PRODUCT_INVALID(400, "Product invalid"),
    PRODUCT_CREATE_FAILED(500, "Product create failed"),
    PRODUCT_DELETE_FAILED(501, "Product delete failed"),
    PRODUCT_GET_FAILED(502, "Product get failed"),
    PRODUCT_SUCCESS(200, "Product success");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;
}
