package com.example.hw27.Service;

import com.example.hw27.ApiException.ApiException;
import com.example.hw27.Model.Blog;
import com.example.hw27.Model.User;
import com.example.hw27.Repository.BlogRepository;
import com.example.hw27.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getBlog(Integer id) {
        List<Blog> blogList = blogRepository.findBlogByUserId(id);
        return blogList;
    }

    public void addBlog(Integer userid, Blog blog) {
        User user = userRepository.findUsersById(userid);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userid, Blog blog, Integer id) {
        Blog oldBlog = blogRepository.findBlogById(id);
        if (oldBlog.getUser().getId() != userid || oldBlog == null) {
            throw new ApiException("Error , blog not found");
        }
        oldBlog.setBody(blog.getBody());
        oldBlog.setTitle(blog.getTitle());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userid, Integer id) {
        Blog oldBlog=blogRepository.findBlogById(id);
        if (oldBlog.getUser().getId() != userid || oldBlog == null){
            throw new RuntimeException("Error , blog not found");
        }
        blogRepository.delete(oldBlog);
    }

    public Blog getBlogById(Integer id){
        Blog blog = blogRepository.findBlogById(id);
        if (blog==null){
            throw new ApiException("Blog not found");
        }
        return  blog;
    }

    public List<Blog> getBlogByTitle(String title){
        List<Blog> blog= blogRepository.findBlogByTitle(title);
        if (blog.isEmpty()){
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public List<Blog> getAllUsersBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        if (blogs.isEmpty()){
            throw new ApiException("Blog not found");
        }
        return blogs;
    }

}