package engine.repositories;

import engine.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {

}
