package com.market.sale.adapters.in.controller;

import com.market.sale.adapters.in.controller.mapper.SaleRequestMapper;
import com.market.sale.adapters.in.controller.request.SaleRequest;
import com.market.sale.application.ports.in.CreateSaleInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final CreateSaleInputPort createSaleInputPort;
    private final SaleRequestMapper saleRequestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSale(@Valid @RequestBody SaleRequest saleRequest) {
        log.info("Creating the sale...");
        this.createSaleInputPort.create(this.saleRequestMapper.saleRequestToSale(saleRequest));
        log.info("Sale created with success.");
    }
}
