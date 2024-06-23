package com.nttdata.invoicemanagementapp.exception;

import com.nttdata.invoicemanagementapp.dto.response.InvoiceResponse;
import com.nttdata.invoicemanagementapp.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<InvoiceResponse> invoiceNotFoundException(InvoiceNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new InvoiceResponse<>(Util.FAILED,ex.getMessage(),null));

    }

    @ExceptionHandler(InvoiceCreationException.class)
    public ResponseEntity<InvoiceResponse> invoiceCreationException(InvoiceCreationException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new InvoiceResponse<>(Util.FAILED,ex.getMessage(),null));

    }
}
