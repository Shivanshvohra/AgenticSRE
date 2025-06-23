package org.example;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class AzureDevOpsPRService {

    private final String azurePAT = "";
    private final String organization = "dpwhocargoes";
    private final String project = "Cargoes Customs UK";
    private final String repoId = "";

    public void createPullRequest() {
        String encodedProject = URLEncoder.encode(project, StandardCharsets.UTF_8);
        String url = String.format(
                "https://dev.azure.com/%s/%s/_apis/git/repositories/%s/pullrequests?api-version=7.1-preview.1",
                organization, encodedProject, repoId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("", azurePAT);


        PullRequestPayload payload = new PullRequestPayload(
                "refs/heads/feature/auto-fix",
                "refs/heads/main",
                "bugfix: handled null pointer",
                "Auto-generated fix"
        );

        HttpEntity<PullRequestPayload> request = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }

}


