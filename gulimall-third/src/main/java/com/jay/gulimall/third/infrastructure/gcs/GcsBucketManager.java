package com.jay.gulimall.third.infrastructure.gcs;

import com.google.cloud.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageFactory;
import com.google.cloud.storage.StorageOptions;
import com.jay.gulimall.third.config.GcsBucketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GcsBucketManager {

    private final Map<String, Storage> storageMap = new HashMap<>();

    @Autowired
    public GcsBucketManager(GcsBucketConfig config) throws IOException {
        for (Map.Entry<String, GcsBucketConfig.BucketProperties> entry : config.getBuckets().entrySet()) {
            String key = entry.getKey();
            GcsBucketConfig.BucketProperties props = entry.getValue();

            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new FileInputStream(props.getCredentialsPath()));

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId(props.getProjectId())
                    .build()
                    .getService();

            storageMap.put(key, storage);
        }
    }

    public Storage getStorage(String bucketKey) {
        return storageMap.get(bucketKey);
    }

    public String getBucketName(String bucketKey, GcsBucketConfig config) {
        return config.getBuckets().get(bucketKey).getName();
    }

}

