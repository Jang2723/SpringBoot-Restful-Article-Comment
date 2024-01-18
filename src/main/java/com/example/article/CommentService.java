package com.example.article;

import com.example.article.dto.CommentDto;
import com.example.article.entity.Article;
import com.example.article.entity.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentDto create(Long articleId, CommentDto dto){
        Optional<Article> optionalArticle
                = articleRepository.findById(articleId);
        // 실존하는 게시글인지 확인
        if (optionalArticle.isEmpty()) // 존재하지 않음
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Comment newComment = new Comment(
                dto.getContent(),
                dto.getWriter(),
                optionalArticle.get()
        );

        return CommentDto.fromEntity(commentRepository.save(newComment));
    }

    public List<CommentDto> readAll(Long articleId){
        // 게시글 존재 여부에 따른 데러 반환
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<CommentDto> commentList = new ArrayList<>();
        // articleId를 바탕으로 댓글 조회
        List<Comment> comments = commentRepository
                .findAllByArticleId(articleId);
        for (Comment entity: comments){
            commentList.add(CommentDto.fromEntity(entity));
        }
        return commentList;
    }

    public CommentDto update(
            Long articleId,
            Long commentId,
            CommentDto dto
    ){
        // 수정대삳 댓글이 존재하는지
        Optional<Comment> optionalComment
                = commentRepository.findById(commentId);
        // 없으면 404
        if (optionalComment.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Comment comment = optionalComment.get();
        if (!articleId.equals(comment.getArticle().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        comment.setContent(dto.getContent());
        comment.setWriter(dto.getWriter());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    public void delete(
            Long articleId,
            Long commentId
    ){
        Optional<Comment> optionalComment
                = commentRepository.findById(commentId);
        // 없으면 404
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Comment comment = optionalComment.get();
        // 댓글- 게시글 ㅂ불일치
        if (!articleId.equals(comment.getArticle().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(commentId);
    }
}
