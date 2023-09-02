package com.oss.gitborad.data.dto;

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
        return new ResponseDTO<>(ResponseCode.SUCCESS, null, null);
    }

    /**
     * A shortcut for create a {@code ResponseDTO} with only failure.
     * @return ResponseDTO&lt;T&gt;
     * @param <T>
     */
    public static <T> ResponseDTO<T> ofFailure() {
        return new ResponseDTO<>(ResponseCode.FAILURE, null, null);
    }
}
