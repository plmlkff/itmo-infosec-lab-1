package ru.itmo.softwaresecurity.lab1.service.impl;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itmo.softwaresecurity.lab1.api.dto.CreatePostRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.PostResponse;
import ru.itmo.softwaresecurity.lab1.domain.repository.PostRepository;
import ru.itmo.softwaresecurity.lab1.domain.repository.UserRepository;
import ru.itmo.softwaresecurity.lab1.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    public Either<HttpStatus, PostResponse> createPost(CreatePostRequest request) {
        var ownerOpt = userRepository.findUserByLogin(request.ownerLogin());

        if (ownerOpt.isEmpty()) return Either.left(HttpStatus.BAD_REQUEST);

        var post = request.toDomain();

        var owner = ownerOpt.get();

        post.setOwner(owner);

        post = postRepository.save(post);

        return Either.right(PostResponse.fromDomain(post));
    }

    @Override
    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream().map(PostResponse::fromDomain).toList();
    }
}
