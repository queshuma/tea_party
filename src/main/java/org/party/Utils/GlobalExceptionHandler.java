package org.party.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * Author: chentao
 * Date: 03 10月 2024
 *
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        // 将异常信息封装到JSON字符串中
        logger.error("Exception -> ", e);
        String errorMessage = "{\"error\": \"" + e.getMessage() + "\"}";
        // 返回500内部服务器错误状态码和异常信息
        return ResponseEntity.internalServerError().body(errorMessage);
    }
}
