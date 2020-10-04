package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Post;
import com.mamalimomen.repositories.PostRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl extends BaseRepositoryImpl<Post, Long> implements PostRepository {
    public PostRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Post> findAllPosts() {
        return findAllByNamedQuery("Post.findAll", Post.class);
    }

    @Override
    public Optional<Post> findOnePostByTitle(String title) {
        return findOneByNamedQuery("Post.findOneByTitle", title, Post.class);
    }
}
