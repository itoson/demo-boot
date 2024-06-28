package org.example.service;

import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return response;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
