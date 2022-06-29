package ir.mapsa.employee;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @org.springframework.beans.factory.annotation.Value("${minio.access.name}")
    String accessKey;
    @org.springframework.beans.factory.annotation.Value("${minio.access.secret}")
    String accessSecret;
    @Value("${mini.url}")
    String minioUrl;

    @Bean
    public MinioClient generateMinioClient(){
        MinioClient minioClient =
                MinioClient.builder().endpoint(minioUrl).credentials(accessKey,accessSecret)
                        .build();
        return minioClient;
    }
}
