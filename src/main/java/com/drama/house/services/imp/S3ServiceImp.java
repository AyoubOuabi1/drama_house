package com.drama.house.services.imp;

import ch.qos.logback.classic.Logger;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.drama.house.services.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImp implements S3Service {


    private final AmazonS3 amazonS3;
   //  @Value("${aws.access.awsAccessKey}")
    private String awsAccessKey = "";

   // @Value("${aws.access.awsSecretKey}")
    private String awsSecretKey = "+ok";

   // @Value("${aws.access.awsRegion}")
    private String awsRegion = "";

   // @Value("${aws.access.awsBucketName}")
    private String bucketName ="";

    public S3ServiceImp() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsRegion)
                .build();
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {

            long contentLength = file.getSize();
            String fileName = generateUniqueFileName(file.getOriginalFilename());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null ));
            System.out.println(amazonS3.getUrl(bucketName, fileName).toString());
            return amazonS3.getUrl(bucketName, fileName).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String generateUniqueFileName(String originalFileName) {
       // String uniqueIdentifier = UUID.randomUUID().toString().replace("-", "");

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        System.out.println("Generating unique file name for file: " + fileNameWithoutExtension);
        return fileNameWithoutExtension + fileExtension;
    }
}
