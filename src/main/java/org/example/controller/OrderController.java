package org.example.controller;

import jp.ne.paypay.model.QRCodeDetails;
import org.example.entity.Order;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public QRCodeDetails createOrder(@RequestBody Order order) {
        logger.info("Received order request: {}", order);
        QRCodeDetails qrCodeDetails = paymentService.createQRCode(order.getUserId().toString(), String.valueOf(System.currentTimeMillis()), order.getAmount());
        logger.info("QR Code details: {}", qrCodeDetails);
        return qrCodeDetails;
    }
}
