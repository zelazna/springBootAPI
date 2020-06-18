package engine.repositories;

import engine.models.Completion;
import engine.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface CompletionRepository extends PagingAndSortingRepository<Completion, Integer> {
    @Query("SELECT C FROM Completion C WHERE C.user = :user")
    Page<Completion> findAllByUser(@Param("user") User user, Pageable pageable);
}
