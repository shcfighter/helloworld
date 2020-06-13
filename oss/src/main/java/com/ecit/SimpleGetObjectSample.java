package com.ecit;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;

/**
 * This sample demonstrates how to upload/download an object to/from 
 * Aliyun OSS using the OSS SDK for Java.
 */
public class SimpleGetObjectSample {

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAIxLoJhrPZ5gtc";
    private static String accessKeySecret = "YXwUZ7aRI6YiLnu51exfMketggAvZE";


    /*private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAI12ggxPowBdaa";
    private static String accessKeySecret = "xIwCYBUPJ4J4eHVUbbMm3mZXi7e4pK";*/


    private static String bucketName = "healthybeauty";

    public static void main(String[] args) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String objectName = "20190803/hello.jpg";
        
        try {

            //ossClient.getObject(new GetObjectRequest(bucketName, "o_318719.jpg"), new File("d://hello.jpg"));

            /*ObjectAcl objectAcl = ossClient.getObjectAcl(bucketName, "o_318719.jpg");
            System.out.println(objectAcl.getPermission().toString());
            System.out.println(objectAcl.toString());*/

            /*AccessControlList accessControlList = ossClient.getBucketAcl("gegejia");
            System.out.println(accessControlList.getOwner());
            System.out.println(accessControlList.getCannedACL().toString());*/

            // 设置URL过期时间为1小时。
            Date expiration = new Date(new Date().getTime() + 5 * 60 * 1000);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println(url);


            AccessControlList accessControlList = ossClient.getBucketAcl(bucketName);
            System.out.println(accessControlList.getOwner());
            System.out.println(accessControlList.getCannedACL().toString());



        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }
    }

    
}