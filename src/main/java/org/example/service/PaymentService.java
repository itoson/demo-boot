package org.example.service;

import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    @Autowired
    private PaymentApi paymentApi;

    public QRCodeDetails createQRCode(String userId, String orderId, int amount) {
        QRCode qrCode = new QRCode();
        qrCode.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
        qrCode.setMerchantPaymentId(orderId);
        qrCode.setCodeType("ORDER_QR");
        qrCode.setOrderDescription("Order payment");
        qrCode.isAuthorization(false);
        qrCode.setRedirectUrl("https://www.eagletech-global.com");
        qrCode.setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);
        qrCode.setUserAgent("Mozilla/5.0");

        try {
            QRCodeDetails response = paymentApi.createQRCode(qrCode);
            startPolling(orderId);
            return response;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startPolling(String merchantPaymentId) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable poller = () -> {
            PaymentDetails response = null;
            try {
                response = paymentApi.getPaymentDetails(merchantPaymentId);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
            // Printing if the method call was SUCCESS, this does not mean the payment was a success
            System.out.println("-----------" + response.getResultInfo().getCode());
            // Printing if the transaction status for the code has COMPLETED/ AUTHORIZED
            System.out.println("-----------" + response.getData().getStatus());

//                    // Creating the payload to refund a Payment, additional parameters can be added basis the API Documentation
//                    Refund refund = new Refund();
//                    refund.setAmount(new MoneyAmount().amount(66).currency(MoneyAmount.CurrencyEnum.JPY));
//                    refund.setMerchantRefundId("merchant_refund_id");
//                    refund.setPaymentId(response.getData().getPaymentId());
//                    refund.setRequestedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//                    refund.setReason("reason for refund");

//                    // Calling the method to refund a Payment
//                    RefundDetails refundPayment = paymentApi.refundPayment(refund);
//                    // Printing if the method call was SUCCESS
//                    System.out.println(refundPayment.getResultInfo().getCode());

//                    if ("COMPLETED".equals(status)) {
//                        System.out.println("Payment successful!");
//                        scheduler.shutdown(); // 停止轮询
//                    } else if ("AUTHORIZED".equals(status)) {
//                        System.out.println("Payment authorized, but not yet completed.");
//                    } else if ("CREATED".equals(status)) {
//                        System.out.println("Payment created, waiting for completion.");
//                    } else if ("CANCELLED".equals(status)) {
//                        System.out.println("Payment cancelled.");
//                        scheduler.shutdown(); // 停止轮询
//                    } else if ("EXPIRED".equals(status)) {
//                        System.out.println("Payment expired.");
//                        scheduler.shutdown(); // 停止轮询
//                    }
        };

        // 每隔5秒执行一次
        scheduler.scheduleAtFixedRate(poller, 0, 30, TimeUnit.SECONDS);
    }

}
