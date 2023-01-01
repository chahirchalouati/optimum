package com.crcl.storage;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("files")
@AllArgsConstructor
public class StorageController {
    private final MinioClient minioClient;

    @GetMapping
    public List<?> testMinio() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.listBuckets();
    }

    @GetMapping("/{tag}")
    public void getObject(@PathVariable("tag") String tag, HttpServletResponse response) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        final GetObjectArgs getObjectArgs = GetObjectArgs.builder().matchETag(tag).bucket("storage").build();
        final InputStream inputStream = minioClient.getObject(getObjectArgs);
        response.addHeader("Content-disposition", "attachment;filename=" + tag);
        response.setContentType(URLConnection.guessContentTypeFromName(tag));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @PostMapping
    public ResponseEntity<?> addAttachment(@RequestParam("file") MultipartFile file) {
        try {
            final PutObjectArgs storage = PutObjectArgs.builder()
                    .object(file.getName())
                    .stream(file.getInputStream(), file.getSize(), file.getSize())
                    .bucket("storage")
                    .contentType("application/octet-stream").build();
            final var objectWriteResponse = minioClient.putObject(storage);
            return ResponseEntity.ok(objectWriteResponse);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(NestedExceptionUtils.getMostSpecificCause(exception));
        }
    }
}
