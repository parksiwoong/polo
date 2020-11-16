package popor.com.enums.bbs;


public enum BoardResponseResult {
    NO_MATCHING_ID,  // 일치하는 게시판 ID 없음
    NOT_AUTHORIZED,  // 게시판 읽을 권한 없음
    OKAY             // 정상
}