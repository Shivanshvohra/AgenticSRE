package org.example;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class GitService {

    // This is where repo will be cloned (a temp folder)
    private static final File WORK_DIR = new File("temp-repo");

    public void automateGitFlow(GitServicePayload gitServicePayload) throws Exception {

        String REPO_URL = gitServicePayload.getRepoUrl();
        String BRANCH_NAME = gitServicePayload.getBranchName();
        String COMMIT_MSG = gitServicePayload.getCommitMessage();

        // Step 1: Clean existing temp-repo directory (if it exists)
        if (WORK_DIR.exists()) {
            deleteRecursively(WORK_DIR);
        }
        WORK_DIR.mkdirs();

        // 2. Clone the repo into temp-repo directly
        GitCommandExecutor.runCommand(List.of("git", "clone", REPO_URL, "."), WORK_DIR);

        // Now WORK_DIR is the repo directory
        File repoDir = WORK_DIR;

        // 3. Create a new branch inside the repo
        GitCommandExecutor.runCommand(List.of("git", "checkout", "-b", BRANCH_NAME), repoDir);

        // 4. Simulate a code edit by appending to README.md
        Files.writeString(
                Path.of(repoDir.getPath(), "README.md"),
                "\n// auto code\n",
                java.nio.file.StandardOpenOption.APPEND
        );

        // 5. Stage the file changes
        GitCommandExecutor.runCommand(List.of("git", "add", "."), repoDir);

        // 6. Commit the changes
        GitCommandExecutor.runCommand(List.of("git", "commit", "-m", COMMIT_MSG), repoDir);

        // 7. Push the new branch to the remote
        GitCommandExecutor.runCommand(List.of("git", "push", "origin", BRANCH_NAME), repoDir);
    }

    // Recursively delete a folder and all its contents
    private void deleteRecursively(File file) throws IOException {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursively(child);
            }
        }
        Files.delete(file.toPath());
    }
}
