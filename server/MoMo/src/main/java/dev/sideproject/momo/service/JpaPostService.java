package dev.sideproject.momo.service;

import dev.sideproject.momo.entity.PostEntity;
import dev.sideproject.momo.entity.TopicEntity;
import dev.sideproject.momo.entity.UserEntity;
import dev.sideproject.momo.model.PostDto;
import dev.sideproject.momo.repository.PostRepository;
import dev.sideproject.momo.repository.TopicRepository;
import dev.sideproject.momo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaPostService implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;



    public JpaPostService(PostRepository postRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }


    @Override
    public PostDto create(Long topicId, PostDto dto) {

        if (!this.topicRepository.existsById(topicId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!this.userRepository.existsById(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        TopicEntity topicEntity = this.topicRepository.findById(topicId).get();
        UserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();

        PostEntity postEntity = new PostEntity();
        postEntity.setContent(dto.getContent());

        postEntity.setWriter(userEntity);
        postEntity.setTopicEntity(topicEntity);
        postEntity = this.postRepository.save(postEntity);

        return new PostDto(
                postEntity.getId(),
                postEntity.getContent(),
                postEntity.getWriter().getId(),
                postEntity.getTopicEntity().getId(),
                postEntity.getTopicEntity().getTitle(),
                postEntity.getCreateAt()
        );
    }

    @Override
    public PostDto read(Long topicId, Long postId) {
        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getTopicEntity().getId().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new PostDto(
                postEntity.getId(),
                postEntity.getContent(),
                postEntity.getWriter().getId(),
                postEntity.getTopicEntity().getId(),
                postEntity.getTopicEntity().getTitle(),
                postEntity.getCreateAt()
        );
    }

    @Override
    public Collection<PostDto> readAll(Long topicId) {
        Optional<TopicEntity> topicEntityOptional = this.topicRepository.findById(topicId);
        TopicEntity topicEntity = topicEntityOptional.get();
        List<PostDto> postDtoList = new ArrayList<>();
        topicEntity.getPostEntityList().forEach(
                postEntity -> postDtoList.add( new PostDto(
                        postEntity.getId(),
                        postEntity.getContent(),
                        postEntity.getWriter().getId(),
                        postEntity.getTopicEntity().getId(),
                        postEntity.getTopicEntity().getTitle(),
                        postEntity.getCreateAt()
                        )
                )
        );
        return postDtoList;
    }

    @Override
    public boolean update(Long topicId, PostDto dto, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();

        if (!postEntity.getTopicEntity().getId().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!postEntity.getWriter().getId().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setContent(
                dto.getContent() == null ? postEntity.getContent() : dto.getContent());

        this.postRepository.save(postEntity);
        return true;
    }

    @Override
    public boolean delete(Long topicId, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getTopicEntity().getId().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.postRepository.deleteById(postId);

        return true;
    }
}