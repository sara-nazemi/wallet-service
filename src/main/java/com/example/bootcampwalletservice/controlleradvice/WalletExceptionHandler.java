package com.example.bootcampwalletservice.controlleradvice;

import com.example.bootcampwalletservice.models.responseFormat.WalletResponse;
import com.example.bootcampwalletservice.serviceexception.WalletException;
import com.example.bootcampwalletservice.util.ResourceBundleUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class WalletExceptionHandler {

    private final ResourceBundleUtil resourceBundleUtil;

    @Autowired
    public WalletExceptionHandler(ResourceBundleUtil resourceBundleUtil) {
        this.resourceBundleUtil = resourceBundleUtil;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody WalletResponse<?> handleMethodTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {

        String propertyName = e.getPropertyName();
        Class<?> requiredType = e.getRequiredType();

        return WalletResponse.builder()
                .message("input parameter " + propertyName + " required this type : " + requiredType)
                .code("internal.server.error")
                .data(null)
                .date(new Date())
                .hasError(true)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody WalletResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String code1 = "";
        for (FieldError fieldError : fieldErrors) {
            code1 = fieldError.getDefaultMessage();
        }
        if (code1 == null || code1.isEmpty()) {
            code1 = "wallet.method.argument.is.not.valid";
        }
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage(code1, request.getHeader("lang")))
                .code(code1)
                .data(null)
                .date(new Date())
                .hasError(true)
                .build();
    }

    @ExceptionHandler(WalletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody WalletResponse<?> walletExceptionHandler(WalletException walletException, HttpServletRequest request) {


        return WalletResponse.builder()
                .date(new Date())
                .code(walletException.getMessage())
                .message(resourceBundleUtil.getMessage(walletException.getMessage(), request.getHeader("lang")))
                .data(null)
                .hasError(true)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody WalletResponse<?> handleException(Exception exception, HttpServletRequest request) {
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage(exception.getMessage(), request.getHeader("lang")))
                .code(exception.getMessage())
                .date(new Date())
                .data(null)
                .hasError(true)
                .build();
    }
}
