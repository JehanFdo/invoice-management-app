package com.nttdata.invoicemanagementapp.service;

import com.nttdata.invoicemanagementapp.dto.request.InvoiceRequest;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    InvoiceRequest createInvoice(InvoiceRequest invoiceRequest);

    InvoiceRequest updateInvoice(Long id, InvoiceRequest invoiceRequest);

    List<InvoiceRequest> getInvoices();

    InvoiceRequest getInvoiceById(Long id);

    void deleteInvoice(Long id);
}
