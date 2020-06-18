package engine.services;

import engine.models.Quiz;
import engine.repositories.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuizService {

    final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Optional<Quiz> get(int id) {
        return quizRepository.findById(id);
    }

    public Page<Quiz> getAll(PageRequest pageRequest) {
        return quizRepository.findAll(pageRequest);
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }
}
