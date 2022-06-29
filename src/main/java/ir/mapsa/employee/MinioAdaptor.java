package ir.mapsa.employee;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioAdaptor {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.access.name}")
    String defaultBucketName;
    @Value("${minio.access.folder}")
    String defaultBaseFolder;

    public String uploadFile(MultipartFile files) {
        try {
            String name = System.currentTimeMillis() + files.getOriginalFilename();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                    PutObjectArgs.builder().bucket(defaultBucketName).object(name).stream(
                                    files.getInputStream(), files.getSize(), -1)
                            .contentType(files.getContentType())
                            .build());

            return objectWriteResponse.object();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }


    }
    public byte[] getFile(String key) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try{
            InputStream obj =  minioClient.getObject(GetObjectArgs.builder().bucket(defaultBucketName)
                    .object(key).build());
            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
