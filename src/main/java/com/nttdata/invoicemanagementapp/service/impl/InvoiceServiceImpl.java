package com.nttdata.invoicemanagementapp.service.impl;

import com.nttdata.invoicemanagementapp.dto.request.InvoiceRequest;
import com.nttdata.invoicemanagementapp.exception.InvoiceCreationException;
import com.nttdata.invoicemanagementapp.exception.InvoiceNotFoundException;
import com.nttdata.invoicemanagementapp.mapper.InvoiceMapper;
import com.nttdata.invoicemanagementapp.repository.InvoiceRepository;
import com.nttdata.invoicemanagementapp.service.InvoiceService;
import com.nttdata.invoicemanagementapp.util.Util;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private InvoiceRepository repository;

    private InvoiceMapper mapper;

    @Override
    public InvoiceRequest createInvoice(InvoiceRequest invoiceRequest) {
        logger.info("Inside the createInvoice method");
        return Stream.of(invoiceRequest)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .findFirst()
                .orElseThrow(
                        () -> new InvoiceCreationException("Invoice Creation Failed")
                );
    }

    @Override
    @Transactional
    public InvoiceRequest updateInvoice(Long id, InvoiceRequest invoiceRequest) {
        logger.info("Inside the updateInvoice method");
        return repository.findById(id)
                .map(i -> mapper.toEntity(invoiceRequest))
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> new InvoiceNotFoundException(Util.INVOICE_NOT_FOUND_MESSAGE + id));

    }

    @Override
    public List<InvoiceRequest> getInvoices() {
        logger.info("Inside the getInvoices method");
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteInvoice(Long id) {
        logger.info("Inside the deleteInvoice method");
        if (!repository.existsById(id)) {
            logger.info("Invoice Not found for the given Id :: {} ",id);
            throw new InvoiceNotFoundException(Util.INVOICE_NOT_FOUND_MESSAGE + id);
        }
        repository.deleteById(id);
        logger.info("Inside the deleteInvoice method :: Deleted :: {} ", id);
    }

    @Override
    public InvoiceRequest getInvoiceById(Long id) {
        logger.info("Inside the getInvoiceById method");
        return repository.findById(id)
                .stream()
                .findFirst()
                .map(mapper::toDto)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not found for the given Id : " + id));
    }
}
