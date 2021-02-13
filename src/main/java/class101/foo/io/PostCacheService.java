package class101.foo.io;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PostCacheService {

    private final PostRepository postRepository;

    public PostCacheService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private Page<Post> firstPostPage;

    @Scheduled(cron = "* * * * * *")
    public void updateFirstPostPage() {
        firstPostPage = postRepository.findAll(PageRequest.of(0, 20, Sort.by("id").descending()));
    }

    public Page<Post> getFirstPostPage() {
        return firstPostPage;
    }
}
