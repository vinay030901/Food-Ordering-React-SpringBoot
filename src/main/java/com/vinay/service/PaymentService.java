package com.vinay.service;

import com.vinay.models.Order;
import com.vinay.response.PaymentResponse;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws Exception;

}
