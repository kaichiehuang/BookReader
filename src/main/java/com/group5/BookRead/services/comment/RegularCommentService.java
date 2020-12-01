package com.group5.BookRead.services.comment;

import com.group5.BookRead.models.Comment;
import com.group5.BookRead.repositories.CommentRepository;
import com.group5.BookRead.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegularCommentService implements CommentService {


    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    /**
     * get comments on the book
     * @param bookId
     * @return
     */
    @Override
    public List<ResponseComment> getComments(final int bookId)
            throws Exception {
        List<Comment> comments =
                commentRepository.getCommentsByBookId(bookId);
        if (comments == null) {
            throw new Exception("can not load comments");
        }

        List<ResponseComment> res = new ArrayList<>();
        for (Comment comment : comments) {
            String username = userService.findByUserId(comment.getUserId())
                    .getUsername();
            res.add(new ResponseComment(
                    comment.getId(),
                    comment.getUserId(),
                    comment.getBookId(),
                    comment.getRating(),
                    comment.getContent(),
                    comment.getTimestamp(),
                    username
            ));
        }
        return res;
    }

    /**
     * Create a comment on the book
     * @param comment
     * @return
     */
    @Override
    public Comment save(final Comment comment)
            throws SQLIntegrityConstraintViolationException {

        commentRepository.insert(comment);
        return getComment(
                comment.getUserId(),
                comment.getBookId());
    }

    /**
     * get the comment created by the user on the book
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public Comment getComment(final int userId, final int bookId) {
        List<Comment> ls = commentRepository.getCommentsByUserIdAndBookId(
                userId, bookId);
        if (ls.size() == 0) {
            return null;
        }
        return ls.get(0);
    }
}
