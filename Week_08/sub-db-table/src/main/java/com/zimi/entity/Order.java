package com.zimi.entity;


import lombok.Data;

import java.util.Date;

/**
 * 订单表
 */
@Data
public class Order {

    private Long orderId;

    private Long payment;

    private Integer paymentType;

    private Long postFee;

    private Integer status;

    private Date createTime;

    private Date updateTime;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;

    private String shippingName;
    private String shippingCode;

    private Long userId;

    private String buyerMessage;

    private String buyerNick;

    private Integer buyerRate;
    private Integer buyerAddress;

}
