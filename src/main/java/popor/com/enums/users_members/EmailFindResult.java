package popor.com.enums.users_members;

public enum EmailFindResult {
    NOT_NAME, //없는 이름
    NOT_CONTACT, // 없는 전화번호

    NORMALIZATION_FAILURE, //빈칸
    FAILURE, // 찾을수 없는 회원
    SUCCESS; // 성공
}
