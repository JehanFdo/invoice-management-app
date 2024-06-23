package com.nttdata.invoicemanagementapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String invoiceNo;
    private String customer;
    private String email;
    private String invoiceDate;
    private double amount;
    private String status;
}
