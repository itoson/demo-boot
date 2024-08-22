package org.example.abc;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnsExample {
    private static final Logger logger = LoggerFactory.getLogger(SnsExample.class);

    public static void main(String[] args) {
        // 设置AWS区域
        Region region = Region.AP_NORTHEAST_1; // 选择适合你的区域
        SnsClient snsClient = SnsClient.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        // 手机号格式: +国家代码+手机号 (例如: +819012345678)
        String phoneNumber = "+819018089304";
        String message = "Your verification code is 123456";

        // 创建发布请求
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();

        try {
            // 发送短信
            PublishResponse result = snsClient.publish(request);
            logger.info("Message sent. Message ID: {}", result.messageId());
        } catch (SnsException e) {
            logger.error("Failed to send message. Error: {}", e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
        } finally {
            snsClient.close();
        }
    }
}
