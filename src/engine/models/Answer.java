package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    @JsonProperty("answer")
    public int[] selectedOptions;
}
