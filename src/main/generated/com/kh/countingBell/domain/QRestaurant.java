package com.kh.countingBell.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 288475251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final QFood food;

    public final QLocation location;

    public final QMember member;

    public final StringPath resAddr = createString("resAddr");

    public final StringPath resClose = createString("resClose");

    public final NumberPath<Integer> resCode = createNumber("resCode", Integer.class);

    public final StringPath resDesc = createString("resDesc");

    public final StringPath resName = createString("resName");

    public final StringPath resOpenHour = createString("resOpenHour");

    public final StringPath resPhone = createString("resPhone");

    public final NumberPath<Integer> resPicks = createNumber("resPicks", Integer.class);

    public final StringPath resPicture = createString("resPicture");

    public QRestaurant(String variable) {
        this(Restaurant.class, forVariable(variable), INITS);
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurant(PathMetadata metadata, PathInits inits) {
        this(Restaurant.class, metadata, inits);
    }

    public QRestaurant(Class<? extends Restaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

