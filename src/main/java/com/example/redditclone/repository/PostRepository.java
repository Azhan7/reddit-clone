package com.example.redditclone.repository;

import com.example.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Long => Type of primary Key
public interface PostRepository extends JpaRepository<Post, Long> {

}
