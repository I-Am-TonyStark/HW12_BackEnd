package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Post;
import com.mamalimomen.repositories.PostRepository;
import com.mamalimomen.services.PostService;

//FIXME
import java.util.Optional;

public class PostServiceImpl extends BaseServiceImpl<Post, Long, PostRepository> implements PostService {
    public PostServiceImpl(PostRepository serviceRepository) {
        super(serviceRepository);
    }

    @Override
    public String createPost() {
        return null;
    }

    @Override
    public Optional<Post> retrievePost() {
        return Optional.empty();
    }

    @Override
    public String updatePost() {
        return null;
    }

    @Override
    public String deletePost() {
        return null;
    }

    @Override
    public void showPosts() {

    }

    @Override
    public void showPostByTitle() {

    }
}
