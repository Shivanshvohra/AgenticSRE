package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubPullRequestPayload {
    private String title;
    private String head;
    private String base;
    private String body;
}

