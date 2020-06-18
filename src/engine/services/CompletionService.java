package engine.services;

import engine.models.Completion;
import engine.models.User;
import engine.repositories.CompletionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class CompletionService {

    final CompletionRepository completionRepository;

    public CompletionService(CompletionRepository completionRepository) {
        this.completionRepository = completionRepository;
    }

    public Page<Completion> getAll(PageRequest pageRequest, User user) {
        return completionRepository.findAllByUser(user, pageRequest);
    }

    public void save(Completion completion) {
        completionRepository.save(completion);
    }
}
