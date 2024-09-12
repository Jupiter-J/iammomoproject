package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {


}
