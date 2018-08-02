package com.oleksa.model.dao.impl;

import java.util.List;
import java.util.Optional;

import com.oleksa.model.dao.CommentDao;
import com.oleksa.model.entity.Comment;
import com.oleksa.model.entity.Record;

public class CommentDaoImpl extends JdbcTemplate<Comment> implements CommentDao {

    @Override
    public Comment create(Comment t) throws Exception {
        super.templateSave(t);
        return t;
    }

    @Override
    public void deleteById(Long id) {
        super.templateDeleteById("delete from Comment where id = :id", id);
    }

    @Override
    public Comment update(Comment t) throws Exception {
        return super.templateUpdate(t);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(super.templateFindById(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        return templateFindAll(Comment.class, "from Comment");
    }

}
