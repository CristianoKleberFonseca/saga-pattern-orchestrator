package com.market.sale.adapters.in.consumer;

import com.market.sale.adapters.out.message.SaleMessage;
import com.market.sale.application.core.domain.enums.SaleEventEnum;
import com.market.sale.application.ports.in.FinalizeSaleInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveSaleToFinalizeConsumer {
    private final FinalizeSaleInputPort finalizeSaleInputPort;

    @KafkaListener(topics = "tp-saga-sale", groupId = "sale-finalize")
    public void receive(SaleMessage saleMessage) {
        if(SaleEventEnum.FINALIZED_SALE.equals(saleMessage.getSaleEvent())) {
            log.info("Ending the sale...");
            this.finalizeSaleInputPort.finalize(saleMessage.getSale());
            log.info("Sale completed successfully.");
        }
    }
}
