package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GitCommandExecutor {


    private static final String GIT_PATH = "/usr/bin/git";

    public static void runCommand(List<String> command, File workingDir) throws Exception {
        // Inject the full git path if command starts with "git"
        if (!command.isEmpty() && "git".equals(command.get(0))) {
            command = command.stream().collect(Collectors.toList());
            command.set(0, GIT_PATH); // Replace "git" with full path
        }

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(workingDir);
        builder.redirectErrorStream(true);

        System.out.println("Running command: " + String.join(" ", command));

        Process process = builder.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print Git CLI output
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed: " + String.join(" ", command));
        }
    }
}
