package com.nttdata.invoicemanagementapp.dto.response;

public record InvoiceResponse<T>(String status, String message, T data) {
}
