package engine.controllers;

import engine.models.*;
import engine.security.UserPrincipal;
import engine.services.CompletionService;
import engine.services.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api")
public class QuizController {

    private final QuizService quizService;
    private final CompletionService completionService;

    public QuizController(QuizService quizService, CompletionService completionService) {
        this.quizService = quizService;
        this.completionService = completionService;
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return getQuizbyId(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public Result checkResponse(@RequestBody Answer answer, @PathVariable int id, Authentication authentication) {
        Quiz quiz = getQuizbyId(id);

        if (Arrays.equals(answer.selectedOptions, quiz.answer)) {
            Completion completion = new Completion();
            completion.setUser(getCurrentUser(authentication));
            completion.setQuiz(quiz);
            completionService.save(completion);
            return Result.SUCCESS;
        }
        return Result.FAILURE;
    }

    @DeleteMapping(path = "/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id, Authentication authentication) {
        Quiz quiz = getQuizbyId(id);
        User user = getCurrentUser(authentication);

        if (user.getEmail().equals(quiz.getUser().getEmail())) {
            quizService.delete(quiz);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping(path = "/quizzes")
    public Page<Quiz> getQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getAll(PageRequest.of(page, 10));
    }

    @GetMapping(path = "/quizzes/completed")
    public Page<Completion> getCompletions(@RequestParam(defaultValue = "0") int page, Authentication authentication) {
        return completionService.getAll(
                PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "completedAt")),
                getCurrentUser(authentication)
        );
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, Authentication authentication) {
        quiz.setUser(getCurrentUser(authentication));
        return quizService.save(quiz);
    }

    private Quiz getQuizbyId(int id) {
        return quizService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private User getCurrentUser(Authentication authentication) {
        return ((UserPrincipal) authentication.getPrincipal()).getUser();
    }
}
