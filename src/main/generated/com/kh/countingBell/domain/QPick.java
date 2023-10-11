package com.kh.countingBell.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPick is a Querydsl query type for Pick
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPick extends EntityPathBase<Pick> {

    private static final long serialVersionUID = 1558683095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPick pick = new QPick("pick");

    public final QMember member;

    public final NumberPath<Integer> pickCode = createNumber("pickCode", Integer.class);

    public final DateTimePath<java.util.Date> pickTime = createDateTime("pickTime", java.util.Date.class);

    public final QRestaurant restaurant;

    public QPick(String variable) {
        this(Pick.class, forVariable(variable), INITS);
    }

    public QPick(Path<? extends Pick> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPick(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPick(PathMetadata metadata, PathInits inits) {
        this(Pick.class, metadata, inits);
    }

    public QPick(Class<? extends Pick> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

