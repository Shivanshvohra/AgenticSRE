package org.example;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PullRequestPayload {
    private String sourceRefName;
    private String targetRefName;
    private String title;
    private String description;

}

