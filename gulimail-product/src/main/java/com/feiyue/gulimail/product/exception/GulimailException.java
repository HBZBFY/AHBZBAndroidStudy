package com.feiyue.gulimail.product.exception;

import com.feiyue.common.utils.R;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(basePackages = "com.feiyue.gulimail.product.controller")
@ResponseBody
@RestControllerAdvice
public class GulimailException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R checkException(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(item -> map.put(item.getField(), item.getDefaultMessage()));
        return  R.error().put("錯誤信息", map);
    }
}
