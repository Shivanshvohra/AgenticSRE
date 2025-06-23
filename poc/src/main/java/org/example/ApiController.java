package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final GitService gitService;
    private final ApiService apiService;
    private final GitHubPRService prService;

    public ApiController(GitService gitService, ApiService apiService, GitHubPRService prService) {
        this.gitService = gitService;
        this.apiService = apiService;
        this.prService = prService;
    }

    @GetMapping("/posts")
    public String fetchPosts() {
        return apiService.getPosts();
    }

    @PostMapping("/run")
    public String runGitFlow(@RequestBody GitServicePayload payload) {
        try {
            gitService.automateGitFlow(payload);
            return "✅ Git automation completed successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Git automation failed: " + e.getMessage();
        }
    }

    @PostMapping("/create-pr")
    public String createPR(@RequestBody GitHubPullRequestPayload payload) {
        prService.createPullRequest(payload);
        return "Pull request created!";
    }

}
