package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitServicePayload {
    private String repoUrl;
    private String branchName;
    private String commitMessage;
}
