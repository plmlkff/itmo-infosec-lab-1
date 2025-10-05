package ru.itmo.softwaresecurity.lab1.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.softwaresecurity.lab1.api.dto.CreatePostRequest;
import ru.itmo.softwaresecurity.lab1.api.dto.PostResponse;
import ru.itmo.softwaresecurity.lab1.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> createPost(
            @RequestBody
            @Valid
            CreatePostRequest createPostRequest
    ){
        return postService.createPost(createPostRequest).fold(
                status -> ResponseEntity.status(status).build(),
                ResponseEntity::ok
        );
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getPosts(Model model){
        List<PostResponse> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        return "posts";
    }
}
