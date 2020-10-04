package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends BaseRepository<Post, Long> {
    List<Post> findAllPosts();

    Optional<Post> findOnePostByTitle(String title);
}
