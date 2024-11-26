package com.gdpu.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
@AllArgsConstructor
@Data
public class AliOssUtil
{
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String region;
   public  String upload(String fileName, byte[] bytes)
   {
       CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);

       // 创建OSSClient实例。
       ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
       clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
       OSS ossClient = OSSClientBuilder.create()
               .endpoint(endpoint)
               .credentialsProvider(credentialsProvider)
               .clientConfiguration(clientBuilderConfiguration)
               .region(region)
               .build();

       try {

           // 创建PutObjectRequest对象。
           PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(bytes));

           // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
           // ObjectMetadata metadata = new ObjectMetadata();
           // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
           // metadata.setObjectAcl(CannedAccessControlList.Private);
           // putObjectRequest.setMetadata(metadata);

           // 上传字符串。
           PutObjectResult result = ossClient.putObject(putObjectRequest);
       } catch (
               OSSException oe) {
           System.out.println("Caught an OSSException, which means your request made it to OSS, "
                   + "but was rejected with an error response for some reason.");
           System.out.println("Error Message:" + oe.getErrorMessage());
           System.out.println("Error Code:" + oe.getErrorCode());
           System.out.println("Request ID:" + oe.getRequestId());
           System.out.println("Host ID:" + oe.getHostId());
       } catch (ClientException ce) {
           System.out.println("Caught an ClientException, which means the client encountered "
                   + "a serious internal problem while trying to communicate with OSS, "
                   + "such as not being able to access the network.");
           System.out.println("Error Message:" + ce.getMessage());
       } finally {
           if (ossClient != null) {
               ossClient.shutdown();
           }
       }
       // 返回文件地址
       String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
       return url;
   }
}
