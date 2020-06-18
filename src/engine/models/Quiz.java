package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Quiz {

    @NotBlank(message = "Title is mandatory")
    String title;
    @NotBlank(message = "text is mandatory")
    String text;

    @NotNull
    @Size(min = 2)
    String[] options;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int[] answer = new int[0];

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private final List<Completion> completions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public List<Completion> getCompletions() {
        return completions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        Arrays.sort(answer);
        this.answer = answer;
    }
}

