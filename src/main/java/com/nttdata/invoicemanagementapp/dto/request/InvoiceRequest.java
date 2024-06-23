package com.nttdata.invoicemanagementapp.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
public class InvoiceRequest {
    private long id;
    private String invoiceNo;
    private String customer;
    private String email;
    private String invoiceDate;
    private double amount;
    private String status;
}
