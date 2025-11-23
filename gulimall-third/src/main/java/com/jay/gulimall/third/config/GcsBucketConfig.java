package com.jay.gulimall.third.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "gcs")
public class GcsBucketConfig {

    private Map<String, BucketProperties> buckets = new HashMap<>();

    public static class BucketProperties {
        private String name;
        private String credentialsPath;
        private String projectId;
        // getters & setters

        public String getName() {
            return name;
        }

        public String getCredentialsPath() {
            return credentialsPath;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCredentialsPath(String credentialsPath) {
            this.credentialsPath = credentialsPath;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }
    }

    public Map<String, BucketProperties> getBuckets() {
        return buckets;
    }

    public void setBuckets(Map<String, BucketProperties> buckets) {
        this.buckets = buckets;
    }
}

