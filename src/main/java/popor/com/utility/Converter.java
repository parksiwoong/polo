package popor.com.utility;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import popor.com.vos.users.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Converter {
    private Converter(){

    }
    public  static String imageToString(MultipartFile image) throws IOException{  //IOE 파일들 인풋아웃풋 예외처리, imageToString 파일들을 스트링형식으로 바꿔주는거
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("data:imge/png;base64,");
    stringBuilder.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getBytes())));
    return stringBuilder.toString();
    }
    public static int stringToInt(String idText,int fallback){  //스트링형식을 인트형식으로 바꿔주는거
        try {
            return Integer.parseInt(idText);
        }catch (Exception ignored){
            return fallback;
        }

    }

    public static void setUserVo(HttpServletRequest request, UserVo userVo) {
        request.getSession().setAttribute("UserVo", userVo);
    }

//     converter.setUserVo(request, null);

    public static UserVo getUserVo(HttpServletRequest request) {
        Object userVoObject = request.getSession().getAttribute("UserVo");
        UserVo userVo = null;
        if (userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        }
        return userVo;
    }

    public static int toInt(String input, int fallback) {
        try {
            return Integer.parseInt(input);
        } catch (Exception ignored) {
            return fallback;
        }
    }



    public static UserVo getUserVo(HttpSession session) {
        Object userVoObject = session.getAttribute(Constant.Apis.ATTRIBUTE_ENTRY_USER_VO);
        UserVo userVo = null;
        if (userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        }
        return userVo;
    }
}
