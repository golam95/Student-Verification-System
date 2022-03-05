package com.std.verification.repogitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.std.verification.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
