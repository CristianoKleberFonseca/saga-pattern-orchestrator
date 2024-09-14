package com.market.sale.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.sale.adapters.out.message.SaleMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer implements Deserializer<SaleMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SaleMessage deserialize(String topic, byte[] data) {
        try {
            if(data == null) {
                return null;
            }
            var src = new String(data, "UTF-8");
            return this.objectMapper.readValue(src, SaleMessage.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to SaleMessage");
        }
    }
}
