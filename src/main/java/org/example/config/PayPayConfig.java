package org.example.config;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPayConfig {

    @Bean
    public ApiClient payPayApiClient() throws ApiException {
        ApiClient apiClient = new ApiClient();
        apiClient.setApiKey("a_zLZ5gw6YqD_t1hc");
        apiClient.setApiSecretKey("vB3kk35H4+7WpKxedYDgKq+q8Ij7ljnby8kP1UFH2CM=");
        apiClient.setAssumeMerchant("794959693614047232");
        apiClient.setProductionMode(false);
        return apiClient;
    }

    @Bean
    public PaymentApi paymentApi(ApiClient apiClient) {
        return new PaymentApi(apiClient);
    }
}

