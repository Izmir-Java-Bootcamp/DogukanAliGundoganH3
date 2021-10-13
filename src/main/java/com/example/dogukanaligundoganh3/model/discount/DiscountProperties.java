package com.example.dogukanaligundoganh3.model.discount;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "discount")
@Data
public class DiscountProperties {
    private int deliveryFee;
    private int deliveryFeeLimit;
    private int percentage;
    private int creditCartCommission;
}
