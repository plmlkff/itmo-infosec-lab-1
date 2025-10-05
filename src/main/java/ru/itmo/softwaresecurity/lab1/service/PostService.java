package ru.itmo.softwaresecurity.lab1.service;

import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import ru.itmo.softwaresecurity.lab1.api.dto.CreatePostRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.PostResponse;

import java.util.List;

public interface PostService {
    Either<HttpStatus, PostResponse> createPost(CreatePostRequest request);

    List<PostResponse> getPosts();
}
