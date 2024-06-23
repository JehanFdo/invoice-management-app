package com.nttdata.invoicemanagementapp.mapper;

import com.nttdata.invoicemanagementapp.dto.request.InvoiceRequest;
import com.nttdata.invoicemanagementapp.model.Invoice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapper {

    private final ModelMapper modelMapper;

    public Invoice toEntity(InvoiceRequest request) {
        return modelMapper.map(request, Invoice.class);
    }

    public InvoiceRequest toDto(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceRequest.class);
    }
}
