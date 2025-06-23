package org.example;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubPRService {

    private final String githubToken = "";
    private final String owner = "Shivanshvohra";
    private final String repo = "Docker-experiments";

    public void createPullRequest(GitHubPullRequestPayload payload) {
        String url = String.format("https://api.github.com/repos/%s/%s/pulls", owner, repo);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<GitHubPullRequestPayload> request = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }
}

