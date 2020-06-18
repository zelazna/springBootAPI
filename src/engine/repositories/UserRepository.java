package engine.repositories;

import engine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);
}
