package com.social.socialnetwork.repository;

import com.social.socialnetwork.model.Post;
import com.social.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByUser(User user);
    @Query("select e from Post e order by e.createDate desc ")
    List<Post> findAllPostDesc();
}
