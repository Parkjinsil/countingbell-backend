DROP TABLE PHOTO CASCADE CONSTRAINTS;
DROP TABLE RESERVATION CASCADE CONSTRAINTS;
DROP TABLE RES_COMMENT CASCADE CONSTRAINTS;
DROP TABLE PICK CASCADE CONSTRAINTS;
DROP TABLE RES_MENU CASCADE CONSTRAINTS;
DROP TABLE DISCOUNT CASCADE CONSTRAINTS;
DROP TABLE REVIEW CASCADE CONSTRAINTS;
DROP TABLE MENU; CASCADE CONSTRAINTS
DROP TABLE RESTAURANT CASCADE CONSTRAINTS;
DROP TABLE LOCATION CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE FOOD CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_PHOTO;
DROP SEQUENCE SEQ_DISCOUNT;
DROP SEQUENCE SEQ_RESERVATION;
DROP SEQUENCE SEQ_MENU;
DROP SEQUENCE SEQ_DISCOUNT;
DROP SEQUENCE SEQ_PICK;
DROP SEQUENCE SEQ_RESTAURANT;
DROP SEQUENCE SEQ_LOCATION;
DROP SEQUENCE SEQ_FOOD;
DROP SEQUENCE SEQ_RES_COMMENT;
DROP SEQUENCE SEQ_REVIEW;

CREATE TABLE MEMBER(
    ID VARCHAR2(20),
    NAME VARCHAR2(200) NOT NULL,
    PASSWORD VARCHAR2(300) NOT NULL,
    PHONE VARCHAR2(20) NOT NULL,
    NICKNAME VARCHAR2(20) UNIQUE,
    GENDER CHAR(1) CHECK(GENDER IN('M', 'F')),
    AGE NUMBER,
    EMAIL VARCHAR2(50),
    ROLE VARCHAR2(10) CHECK(ROLE IN('사장', '고객', '관리자'))
);
CREATE TABLE DISCOUNT(
    DIS_CODE NUMBER,
    DIS_DESC VARCHAR2(100) NOT NULL,
    DIS_PERIOD VARCHAR2(50) NOT NULL,
    RES_CODE NUMBER
);
CREATE TABLE RESTAURANT(
    RES_CODE NUMBER,
    RES_NAME VARCHAR2(200) NOT NULL,
    RES_ADDR VARCHAR2(200) NOT NULL,
    RES_PHONE VARCHAR2(200) NOT NULL,
    RES_OPEN_HOUR VARCHAR2(100),
    RES_CLOSE VARCHAR2(100),
    RES_DESC VARCHAR2(500),
    LOCAL_CODE NUMBER,
    FOOD_CODE NUMBER,
    MENU_CODE NUMBER,
    ID VARCHAR2(20)
);
CREATE TABLE LOCATION(
    LOCAL_CODE NUMBER,
    LOCAL_NAME VARCHAR2(200) NOT NULL
);
CREATE TABLE FOOD(
    FOOD_CODE NUMBER,
    FOOD_TYPE VARCHAR2(100) NOT NULL
);
CREATE TABLE RES_COMMENT(
    RES_COMMENT_CODE NUMBER,
    RES_COMMENT_DETAIL VARCHAR2(200),
    RES_COMMENT_DATE DATE DEFAULT SYSDATE,
    ID VARCHAR2(20),
    REVIEW_CODE NUMBER
);
CREATE TABLE RESERVATION(
    RESER_CODE NUMBER,
    RESER_COM VARCHAR2(200), --complete
    RESER_NO VARCHAR2(200) NOT NULL, --subscriber
    RESER_TIME TIMESTAMP, --SYSTIMESTAMP-INSERT
    ID VARCHAR2(20),
    RES_CODE NUMBER
);
CREATE TABLE REVIEW(
    REVIEW_CODE NUMBER,
--    MEM_ID VARCHAR2(200), -- 일단 만들어놓음
    REVIEW_CONTENT VARCHAR2(200), --리뷰-리뷰내용
    REVIEW_GRADE NUMBER,
    REVIEW_DATE DATE DEFAULT SYSDATE,
    ID VARCHAR2(20),
    RES_CODE NUMBER
);
CREATE TABLE PHOTO(
    RES_PHOTO_CODE NUMBER,
    PHOTO_URL VARCHAR2(300) NOT NULL,
    PHOTO_NAME VARCHAR2(50) NOT NULL,
    RES_CODE NUMBER
);
CREATE TABLE MENU(
    MENU_CODE NUMBER,
    MENU_NAME VARCHAR2(100),
    MENU_PRICE VARCHAR2(50),
    MENU_DESC VARCHAR2(300),
    MENU_PICTURE VARCHAR2(200),
    RES_CODE NUMBER
);
CREATE TABLE PICK(
    PICK_CODE NUMBER,
    RES_CODE NUMBER,
    PICK_TIME DATE DEFAULT SYSDATE,
    ID VARCHAR2(20)
);

CREATE SEQUENCE SEQ_PHOTO;
CREATE SEQUENCE SEQ_MENU;
CREATE SEQUENCE SEQ_DISCOUNT;
CREATE SEQUENCE SEQ_PICK;
CREATE SEQUENCE SEQ_RESTAURANT;
CREATE SEQUENCE SEQ_LOCATION;
CREATE SEQUENCE SEQ_FOOD;
CREATE SEQUENCE SEQ_RES_COMMENT;
CREATE SEQUENCE SEQ_RESERVATION;
CREATE SEQUENCE SEQ_REVIEW;
​
​
ALTER TABLE RESTAURANT ADD CONSTRAINT RESTAURANT_RES_CODE_PK PRIMARY KEY(RES_CODE);
ALTER TABLE LOCATION ADD CONSTRAINT LOCATION_LOCAL_CODE_PK PRIMARY KEY(LOCAL_CODE);
ALTER TABLE FOOD ADD CONSTRAINT FOOD_FOOD_CODE_PK PRIMARY KEY(FOOD_CODE);
ALTER TABLE MEMBER ADD CONSTRAINT MEMBER_ID_PK PRIMARY KEY(ID);
ALTER TABLE DISCOUNT ADD CONSTRAINT DISCOUNT_DIS_CODE_PK PRIMARY KEY(DIS_CODE);
ALTER TABLE RES_COMMENT ADD CONSTRAINT RES_COMMENT_RES_COMMENT_CODE_PK PRIMARY KEY(RES_COMMENT_CODE);
ALTER TABLE PICK ADD CONSTRAINT PICK_PICK_CODE_PK PRIMARY KEY(PICK_CODE);
ALTER TABLE RESERVATION ADD CONSTRAINT RESERVATION_RESER_CODE_PK PRIMARY KEY(RESER_CODE);
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_REVIEW_CODE_PK PRIMARY KEY(REVIEW_CODE);
ALTER TABLE PHOTO ADD CONSTRAINT PHOTO_RES_PHOTO_CODE_PK PRIMARY KEY (RES_PHOTO_CODE);
ALTER TABLE MENU ADD CONSTRAINT MENU_MENU_CODE_PK PRIMARY KEY (MENU_CODE);
​
​
​
​
​
ALTER TABLE RESTAURANT ADD CONSTRAINT RESTAURANT_ID_FK FOREIGN KEY(ID) REFERENCES MEMBER;
ALTER TABLE RESTAURANT ADD CONSTRAINT RESTAURANT_LOCAL_CODE_FK FOREIGN KEY(LOCAL_CODE) REFERENCES LOCATION;
ALTER TABLE RESTAURANT ADD CONSTRAINT RESTAURANT_FOOD_CODE_FK FOREIGN KEY(FOOD_CODE) REFERENCES FOOD;
ALTER TABLE PICK ADD CONSTRAINT PICK_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT;
ALTER TABLE PICK ADD CONSTRAINT PICK_ID_FK FOREIGN KEY(ID) REFERENCES MEMBER;
ALTER TABLE RES_COMMENT ADD CONSTRAINT RES_COMMENT_ID_FK FOREIGN KEY(ID) REFERENCES MEMBER;
ALTER TABLE RES_COMMENT ADD CONSTRAINT RES_COMMENT_REVIEW_CODE_FK FOREIGN KEY(REVIEW_CODE) REFERENCES REVIEW;
ALTER TABLE RESERVATION ADD CONSTRAINT RESERVATION_ID_FK FOREIGN KEY(ID) REFERENCES MEMBER;
ALTER TABLE RESERVATION ADD CONSTRAINT RESERVATION_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT;
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_ID_FK FOREIGN KEY(ID) REFERENCES MEMBER;
ALTER TABLE REVIEW ADD CONSTRAINT REVIEW_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT;
ALTER TABLE MENU ADD CONSTRAINT MENU_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT;
ALTER TABLE DISCOUNT ADD CONSTRAINT DISCOUNT_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT(RES_CODE);
ALTER TABLE PHOTO ADD CONSTRAINT PHOTO_RES_CODE_FK FOREIGN KEY(RES_CODE) REFERENCES RESTAURANT(RES_CODE);
​
​

​
INSERT INTO MENU VALUES(1, '맛있는음식', '10,000원', '사진', '1');
INSERT INTO MENU VALUES(2, '맛없는음식', '20,000원', '사진2', '2');
INSERT INTO MENU VALUES(3, '맛있던음식', '10,002원', '사진2', '3');
INSERT INTO MEMBER VALUES('JS', '박진실', '1111', '1111', '1111', '여', '27', '1111', '고객');
INSERT INTO MEMBER VALUES('SM', '신수민', '2222', '2222', '2222', '여', '27', '2222', '고객');
INSERT INTO MEMBER VALUES('KM', '김경미', '3333', '3333', '3333', '여', '27', '3333', '사장');
INSERT INTO MEMBER VALUES('JH', '최준혁', '4444', '4444', '4444', '남', '27', '4444', '관리자');
INSERT INTO DISCOUNT VALUES(SEQ_DISCOUNT.NEXTVAL, '80퍼센트 할인', '8.18~8.23', '1');
INSERT INTO DISCOUNT VALUES(SEQ_DISCOUNT.NEXTVAL, '70퍼센트 할인', '8.20~8.30', '2');
INSERT INTO RESTAURANT(RES_CODE, RES_NAME, RES_ADDR, RES_PHONE) VALUES(SEQ_RESTAURANT.NEXTVAL, '김밥천국', '서울 강남구', '02-111-1111');
INSERT INTO RESTAURANT(RES_CODE, RES_NAME, RES_ADDR, RES_PHONE) VALUES(SEQ_RESTAURANT.NEXTVAL, '나이스샤워', '서울 강남구', '02-222-2222');
INSERT INTO RESTAURANT(RES_CODE, RES_NAME, RES_ADDR, RES_PHONE) VALUES(SEQ_RESTAURANT.NEXTVAL, '김남완초밥', '서울 강남구', '02-333-3333');
INSERT INTO RES_COMMENT(RES_COMMENT_CODE, RES_COMMENT_DETAIL, RES_PARENT_COMMENT, ID, REVIEW_CODE) VALUES(SEQ_RES_COMMENT.NEXTVAL, 'ㅇ', 'ㅇ', '1', '1');
INSERT INTO PICK(PICK_CODE, RES_CODE, ID) VALUES(SEQ_PICK.NEXTVAL,'1', 'JY');
​
​
SELECT * FROM PICK;
SELECT * FROM RESTAURANT;
SELECT * FROM LOCATION ORDER BY LOCAL_CODE;
SELECT * FROM FOOD;
SELECT * FROM MEMBER;
SELECT * FROM DISCOUNT;
SELECT * FROM MENU;
SELECT * FROM RES_COMMENT;
SELECT * FROM PHOTO;

