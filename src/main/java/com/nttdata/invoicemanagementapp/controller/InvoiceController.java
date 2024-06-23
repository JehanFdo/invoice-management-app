package com.nttdata.invoicemanagementapp.controller;

import com.nttdata.invoicemanagementapp.dto.request.InvoiceRequest;
import com.nttdata.invoicemanagementapp.dto.response.InvoiceResponse;
import com.nttdata.invoicemanagementapp.service.InvoiceService;
import com.nttdata.invoicemanagementapp.util.Util;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/invoices")
@AllArgsConstructor
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);


    private InvoiceService service;

    @PostMapping
    public ResponseEntity<InvoiceResponse<InvoiceRequest>> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        logger.info("Inside createInvoice controller :: Request :: {} ", invoiceRequest);

        InvoiceRequest createdObj = service.createInvoice(invoiceRequest);
        logger.info("Inside createInvoice controller :: Invoice Created :: Response :: {} ", createdObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new InvoiceResponse<>(Util.SUCCESS, "Invoice Has Been Successfully Created", createdObj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponse<InvoiceRequest>> updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequest invoiceRequest) {
        logger.info("Inside updateInvoice controller :: Id :: {} :: Request :: {} ", id,invoiceRequest);
        InvoiceRequest updatedObj = service.updateInvoice(id, invoiceRequest);
        logger.info("Inside updateInvoice controller :: Invoice Updated :: Response :: {} ", updatedObj);
        return ResponseEntity.ok(new InvoiceResponse<>(
                        Util.SUCCESS,
                        "Invoice Has Been Successfully Updated",
                        updatedObj
                )
        );
    }

    @GetMapping
    public ResponseEntity<InvoiceResponse<List<InvoiceRequest>>> getInvoices() {
        logger.info("Inside getInvoices controller");
        List<InvoiceRequest> invoices = service.getInvoices();
        logger.info("Inside getInvoices controller ::  Response :: {} ", invoices);
        return ResponseEntity.ok(
                new InvoiceResponse<>(
                        invoices.isEmpty() ? Util.FAILED : Util.SUCCESS,
                        invoices.isEmpty() ? "No Invoices data Available" : "Invoice Data Found", invoices
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceResponse<InvoiceRequest>> deleteInvoice(@PathVariable long id) {
        logger.info("Inside deleteInvoice controller :: Request Id :: {} ", id);
        service.deleteInvoice(id);
        logger.info("Inside deleteInvoice controller :: Deleted");
        return ResponseEntity.ok(new InvoiceResponse<>(
                        Util.SUCCESS,
                        "Invoice Has Been Successfully Deleted",
                        null
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse<InvoiceRequest>> getInvoiceById(@PathVariable long id) {
        logger.info("Inside getInvoiceById controller :: Request Id :: {} ", id);
        InvoiceRequest invoiceObj = service.getInvoiceById(id);
        logger.info("Inside getInvoiceById controller ::  Response :: {} ", invoiceObj);
        return ResponseEntity.ok(new InvoiceResponse<>(
                        Util.SUCCESS,
                        "Invoice Data Found",
                        invoiceObj
                )
        );

    }

}
