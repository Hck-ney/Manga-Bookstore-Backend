package com.example.demo.external.supabase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    private final RestClient restClient;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-key}")
    private String serviceKey;

    public SupabaseStorageService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public String upload(MultipartFile file) throws IOException {

        String fileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        String url =
                supabaseUrl
                        + "/storage/v1/object/Images/"
                        + fileName;

        restClient
                .post()
                .uri(url)
                .header("apikey", serviceKey)
                .header("Authorization", "Bearer " + serviceKey)
                .header("Content-Type", file.getContentType())
                .body(file.getBytes())
                .retrieve()
                .toBodilessEntity();

        return supabaseUrl
                + "/storage/v1/object/public/Images/"
                + fileName;
    }
}
