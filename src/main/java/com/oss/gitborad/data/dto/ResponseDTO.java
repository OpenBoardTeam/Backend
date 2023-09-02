package com.oss.gitborad.data.dto;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * DTO for consistent response.
 *
 * <pre class="code">
 *     public ResponseEntity&lt;ResponseDTO&gt; example() {
 *         ResponseDTO dto = ResponseDTO.ofSuccess();
 *         return ResponseEntity.ok(dto);
 *     }
 * </pre>
 *
 * @author Yoonki Hong
 * @param <T>
 */
@Getter
public class ResponseDTO<T> {
    private String resultCode;
    private String message;
    private T data;

    private ResponseDTO(String resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    /**
     * Create ResponseDTO manually.
     * @param resultCode
     * @param message
     * @param data
     * @return ResponseDTO&lt;T&gt;
     * @param <T>
     */
    public static <T> ResponseDTO<T> of(String resultCode, String message, T data) {
        return new ResponseDTO<>(resultCode, message, data);
    }

    /**
     * A shortcut for creating a {@code ResponseDTO} with only success.
     * @return ResponseDTO&lt;T&gt;
     * @param <T>
     */
    public static <T> ResponseDTO<T> ofSuccess() {
        return ofSuccess(null);
    }
    public static <T> ResponseDTO<T> ofSuccess(String message) {
        return new ResponseDTO<>(ResponseCode.SUCCESS, message, null);
    }

    /**
     * A shortcut for create a {@code ResponseDTO} with only failure.
     * @return ResponseDTO&lt;T&gt;
     * @param <T>
     */
    public static <T> ResponseDTO<T> ofFailure() {
        return ofFailure(null);
    }
    public static <T> ResponseDTO<T> ofFailure(String message) {
        return new ResponseDTO<>(ResponseCode.FAILURE, message, null);
    }

    /**
     * A shortcut for create a {@code ResponseDTO} with only Unauthorized
     * @return ResponseDTO&lt;T&gt;
     * @param <T>
     */
    public static <T> ResponseDTO<T> ofUnauthorized() {
        return ofUnauthorized(null);
    }
    public static <T> ResponseDTO<T> ofUnauthorized(String message) {
        return new ResponseDTO<>(ResponseCode.UNAUTHORIZED, message, null);
    }
}
