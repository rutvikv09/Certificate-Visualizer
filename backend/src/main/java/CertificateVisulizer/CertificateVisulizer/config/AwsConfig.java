package CertificateVisulizer.CertificateVisulizer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Bean
    public S3Client s3Client() {
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(accessKey,secretKey,sessionToken);
        return S3Client.builder().region(Region.US_EAST_1).credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();
    }
}

