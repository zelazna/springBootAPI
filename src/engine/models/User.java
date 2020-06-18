package engine.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^(.+)@\\w+\\.\\w{2,3}$", message = "Email should be valid")
    String email;

    @Column(nullable = false)
    @Length(min = 5)
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<Completion> completions = new ArrayList<>();

    public User() {
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Completion> getCompletions() {
        return completions;
    }
}
