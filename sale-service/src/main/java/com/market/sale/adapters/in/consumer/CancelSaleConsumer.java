package com.market.sale.adapters.in.consumer;

import com.market.sale.adapters.out.message.SaleMessage;
import com.market.sale.application.core.domain.enums.SaleEventEnum;
import com.market.sale.application.ports.in.CancelSaleInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CancelSaleConsumer {

    private final CancelSaleInputPort cancelSaleInputPort;

    @KafkaListener(topics = "tp-saga-sale", groupId = "sale-cancel")
    public void receive(SaleMessage saleMessage) {
        if(SaleEventEnum.CANCELED_SALE.equals(saleMessage.getSaleEvent())) {
            log.info("Canceling the sale...");
            this.cancelSaleInputPort.cancel(saleMessage.getSale());
            log.info("Sale canceled");
        }
    }
}
