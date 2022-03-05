package com.std.verification.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.std.verification.dto.PostDto;
import com.std.verification.helper.CommonMsg;
import com.std.verification.model.Post;

public interface PostService {
	List<Post> listOfPost();
	CommonMsg saveUPost(PostDto postDto);
	CommonMsg deletePost(Long id);
    Page<Post> findPaginated(Pageable pageable) ;
    Long totalPost();
}
