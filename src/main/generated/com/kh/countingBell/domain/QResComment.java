package com.kh.countingBell.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResComment is a Querydsl query type for ResComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResComment extends EntityPathBase<ResComment> {

    private static final long serialVersionUID = 143742677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResComment resComment = new QResComment("resComment");

    public final QMember member;

    public final NumberPath<Integer> resCommentCode = createNumber("resCommentCode", Integer.class);

    public final DateTimePath<java.util.Date> resCommentDate = createDateTime("resCommentDate", java.util.Date.class);

    public final StringPath resCommentDetail = createString("resCommentDetail");

    public final QReview review;

    public QResComment(String variable) {
        this(ResComment.class, forVariable(variable), INITS);
    }

    public QResComment(Path<? extends ResComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResComment(PathMetadata metadata, PathInits inits) {
        this(ResComment.class, metadata, inits);
    }

    public QResComment(Class<? extends ResComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

