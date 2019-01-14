-- 게시판
CREATE TABLE pj_board (
    num         NUMBER PRIMARY KEY, -- 글번호
    flag        NUMBER DEFAULT 0 NOT NULL, -- 카테고리
    writer      VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    subject     VARCHAR2(50 BYTE) NOT NULL, -- 제목
    content     VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    email       VARCHAR2(30 BYTE), -- 이메일
    readcount   NUMBER DEFAULT 0, -- 조회수
    password    VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF         NUMBER NOT NULL, -- 참조번호
    re_step     NUMBER DEFAULT 0,
    re_level    NUMBER DEFAULT 0,
    ip          VARCHAR2(30 BYTE) NOT NULL,
    reg_date    VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------
-- 댓글

CREATE TABLE pj_sub_board (
    sub_num        NUMBER PRIMARY KEY,
    sub_writer     VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    sub_content    VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    sub_password   VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF            NUMBER NOT NULL, -- 참조번호
    reg_date       VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------
--자유게시판
CREATE TABLE pj_freeboard (
    num         NUMBER PRIMARY KEY, -- 글번호
    writer      VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    subject     VARCHAR2(50 BYTE) NOT NULL, -- 제목
    content     VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    email       VARCHAR2(30 BYTE), -- 이메일
    readcount   NUMBER DEFAULT 0, -- 조회수
    password    VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF         NUMBER NOT NULL, -- 참조번호
    re_step     NUMBER DEFAULT 0,
    re_level    NUMBER DEFAULT 0,
    ip          VARCHAR2(30 BYTE) NOT NULL,
    reg_date    VARCHAR2(20 BYTE) -- 등록일
);
select * from PJ_FREEBOARD;
--------------------------------------------------------------------

-- 자유게시판댓글

CREATE TABLE pj_sub_freeboard (
    sub_num        NUMBER PRIMARY KEY,
    sub_writer     VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    sub_content    VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    sub_password   VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF            NUMBER NOT NULL, -- 참조번호
    reg_date       VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------
--공지사항
CREATE TABLE pj_noticboard (
    num         NUMBER PRIMARY KEY, -- 글번호
    writer      VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    subject     VARCHAR2(50 BYTE) NOT NULL, -- 제목
    content     VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    email       VARCHAR2(30 BYTE), -- 이메일
    readcount   NUMBER DEFAULT 0, -- 조회수
    password    VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF         NUMBER NOT NULL, -- 참조번호
    re_step     NUMBER DEFAULT 0,
    re_level    NUMBER DEFAULT 0,
    ip          VARCHAR2(30 BYTE) NOT NULL,
    reg_date    VARCHAR2(20 BYTE) -- 등록일
);
select * from PJ_FREEBOARD;
--------------------------------------------------------------------

-- 공지사항댓글

CREATE TABLE pj_sub_noticboard (
    sub_num        NUMBER PRIMARY KEY,
    sub_writer     VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    sub_content    VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    sub_password   VARCHAR2(20 BYTE) NOT NULL, -- 비밀번호
    REF            NUMBER NOT NULL, -- 참조번호
    reg_date       VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------
-- Q&A 게시판

CREATE TABLE pj_qnaboard (
    num         NUMBER PRIMARY KEY, -- 글번호
    flag        NUMBER DEFAULT 0 NOT NULL, -- 카테고리
    writer      VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    subject     VARCHAR2(50 BYTE) NOT NULL, -- 제목
    content     VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    email       VARCHAR2(30 BYTE), -- 이메일
    filename    VARCHAR2(300 BYTE) DEFAULT 'nothing.jpg', -- 이미지파일
    readcount   NUMBER DEFAULT 0, -- 조회수
    REF         NUMBER NOT NULL, -- 참조번호
    re_step     NUMBER DEFAULT 0,
    re_level    NUMBER DEFAULT 0,
    ip          VARCHAR2(30 BYTE) NOT NULL,
    reg_date    VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------

-- Q&A 댓글

CREATE TABLE pj_sub_qnaboard (
    sub_num       NUMBER PRIMARY KEY,
    sub_writer    VARCHAR2(20 BYTE) NOT NULL, -- 작성자
    sub_content   VARCHAR2(2000 BYTE) NOT NULL, -- 내용
    REF           NUMBER NOT NULL, -- 참조번호
    reg_date      VARCHAR2(20 BYTE) -- 등록일
);
--------------------------------------------------------------------

-- 멤버

CREATE TABLE pj_member (
    id             VARCHAR2(20 BYTE), -- 유저아이디
    password       VARCHAR2(20 BYTE),
    name           VARCHAR2(20 BYTE),
    rrnum1         VARCHAR2(6), -- 주민등록번호 앞자리
    rrnum2         VARCHAR2(7), -- 주민등록번호 뒷자리
    zipno          VARCHAR2(7 BYTE),
    address1       VARCHAR2(80 BYTE),
    address2       VARCHAR2(50 BYTE),
    tel1           VARCHAR2(3 BYTE),
    tel2           VARCHAR2(4 BYTE),
    tel3           VARCHAR2(4 BYTE),
    email          VARCHAR2(20 BYTE),
    regdate        VARCHAR2(20 BYTE), -- 가입일
    use_flag       CHAR(1 BYTE) DEFAULT 0, -- 탈퇴 여부
    manager_flag   CHAR(1) DEFAULT 0 -- 매니저 여부
);

--------------------------------------------------------------------

-- 상품

CREATE TABLE pj_item (
    num          NUMBER, -- 글번호
    item_id      NUMBER PRIMARY KEY,
    item_kind    VARCHAR2(30 BYTE), -- 상품 유형
    item_name    VARCHAR2(100 BYTE),
    item_price   NUMBER(20),
    item_count   NUMBER(5), -- 판매 상품 수
    item_image   VARCHAR2(300 BYTE) DEFAULT 'nothing.jpg',
    startday     DATE, -- 렌트 시작일
    endday       DATE, -- 렌트 종료일
    trans_type   VARCHAR2(30), -- 거래 유형
    reg_date     VARCHAR2(20) -- 상품 등록일
);
--------------------------------------------------------------------
-- 렌트

CREATE TABLE pj_rent (
    rent_id           NUMBER PRIMARY KEY,
    rent_seller       VARCHAR2(20),
    rent_buyer        VARCHAR2(20),
    item_id           NUMBER,
    item_kind         VARCHAR2(10 BYTE), -- 상품 유형
    item_name         VARCHAR2(100 BYTE),
    item_price        NUMBER(20),
    item_count        NUMBER(5), -- 판매 상품 수
    item_image        VARCHAR2(300 BYTE) DEFAULT 'nothing.jpg',
    startday          DATE, -- 렌트 시작일
    endday            DATE, -- 렌트 종료일
    trans_type        VARCHAR2(30), -- 거래 유형
    deliveryname      VARCHAR2(20),
    deliverytel       VARCHAR2(15),
    deliveryaddress   VARCHAR2(200),
    sanction          VARCHAR2(10) DEFAULT '준비중',
    resanction        VARCHAR2(10),
    reg_date          VARCHAR2(20) -- 상품 등록일
);

-----------------------------------------------------------
-- 채팅
create table chat(
	chatID NUMBER PRIMARY KEY,
	fromID VARCHAR2(20),
	toID VARCHAR2(20),
	chatContent VARCHAR2(100),
	chatTime DATE DEFAULT SYSDATE
);


CREATE SEQUENCE chat_seq
START WITH 1
INCREMENT BY 1
;
select * from chat;
select * from PJ_MEMBER;
update pj_member set manager_flag = '1';
