package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Post;

import java.util.Optional;

public interface PostService extends BaseService<Post, Long> {

    String createPost();

    Optional<Post> retrievePost();

    String updatePost();

    String deletePost();

    void showPosts();

    void showPostByTitle();
}
