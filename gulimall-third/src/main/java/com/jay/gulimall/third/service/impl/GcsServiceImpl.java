package com.jay.gulimall.third.service.impl;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import com.jay.gulimall.third.config.GcsBucketConfig;
import com.jay.gulimall.third.infrastructure.gcs.GcsBucketManager;
import com.jay.gulimall.third.service.GcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service("cgsService")
public class GcsServiceImpl implements GcsService {

    @Autowired
    private GcsBucketManager bucketManager;

    @Autowired
    private GcsBucketConfig bucketConfig;
    @Override
    public String getUploadUrl(String itemName) {

        Storage storage = bucketManager.getStorage("user-uploads");
        String bucketName = bucketManager.getBucketName("user-uploads", bucketConfig);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String dataStr = format.format(calendar.getTime());

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, dataStr + "/" +itemName).build();

        URL signedUrl = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                Storage.SignUrlOption.withV4Signature());

        return signedUrl.toString();


    }
}
