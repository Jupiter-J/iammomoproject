package dev.sideproject.momo.repository;

import dev.sideproject.momo.entity.UserEntity;
import dev.sideproject.momo.model.UserPostInterface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT u.uid ,p.content, p.create_at, p.pid, t.tid, t.title\n" +
                    "    FROM user_momo u \n" +
                    "    INNER JOIN post p ON u.uid = p.user_id JOIN topic t ON t.tid = p.topic_id\n" +
                    "where u.uid = :id"
    )
    List<UserPostInterface> findByUserPost(@PathVariable("id") Long id);


}
