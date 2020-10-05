package com.mamalimomen.services.impl;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Post;
import com.mamalimomen.repositories.PostRepository;
import com.mamalimomen.repositories.impl.PostRepositoryImpl;
import com.mamalimomen.services.PostService;

import javax.persistence.EntityManager;
import java.util.Optional;

//FIXME

public class PostServiceImpl extends BaseServiceImpl<Post, Long, PostRepository> implements PostService {
    public PostServiceImpl(EntityManager em) {
        super(new PostRepositoryImpl(em));
    }

    @Override
    public Optional<Post> createPost() {
        return null;
    }

    @Override
    public Optional<Post> retrievePost() {
        Post post = new Post();
        while (true) {
            try {
                System.out.print("Post Title: ");
                String postTitle = SingletonScanner.readLine();
                post.setTitle(postTitle);
                return baseRepository.findOnePostByTitle(postTitle);
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!\n");
            }
        }
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
