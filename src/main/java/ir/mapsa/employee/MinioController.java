package ir.mapsa.employee;

import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MinioController {
    @Autowired
    MinioAdaptor minioAdaptor;

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) {
        String gKey = minioAdaptor.uploadFile(files);
        Map<String, String> result = new HashMap<>();

        result.put("GeneratedKey", gKey);
        return result;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        byte[] data = minioAdaptor.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok().
                contentLength(data.length)
                .header("Conent-type",
                        "application/octet-stream")
                .header("Content-disposition",
                        "attachment; filename=\"" + file + "\"")
                .body(resource);
    }
}
