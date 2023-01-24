package com.social.socialnetwork.repository;

import com.social.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
