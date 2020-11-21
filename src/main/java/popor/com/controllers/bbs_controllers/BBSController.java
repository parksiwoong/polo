package popor.com.controllers.bbs_controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import popor.com.enums.bbs.BoardWriteResult;
import popor.com.services.bbs_services.BoardService;
import popor.com.utility.Converter;
import popor.com.utility.Variable;
import popor.com.vos.*;
import popor.com.vos.bbs.*;
import popor.com.vos.users.UserVo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class BBSController {

    private final BoardService boardService;

    @Autowired
    public BBSController(BoardService boardService) {
        this.boardService = boardService;

    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String boardGet(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(name = "id", defaultValue = "") String id,
                           @RequestParam(name = "page", defaultValue = "1") String page) throws
            SQLException {
        BoardVo boardVo = new BoardVo(id, page);
        BoardResponseVo boardResponseVo = this.boardService.getArticles(request.getSession(), boardVo); //겟아티클스에서 id를 받아와 ?id=... 을 받아오는거
        request.setAttribute("BoardResponseVo", boardResponseVo);
        return "bbs/board";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchGet(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "id", defaultValue = "") String id,
                            @RequestParam(name = "page", defaultValue = "") String page,
                            @RequestParam(name = "what", defaultValue = "") String what,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) throws
            SQLException {
        SearchVo searchVo = new SearchVo(id, page, what, keyword);
        BoardResponseVo boardResponseVo = this.boardService.search(request.getSession(), searchVo);
        request.setAttribute("BoardResponseVo", boardResponseVo);
        return "bbs/board";
    }


    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGet(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(name = "id", defaultValue = "") String id,
                           //jsp의  id와 연동시키려고 이렇게 쓰고 id를 스트링형식으로 변환하고 디폴트값은 빈값으로 줘야
                           // 아무것도 안뜨게 겟방식으로 사용하는거
                           @RequestParam(name = "page", defaultValue = "") String page) {
        return "bbs/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public void writePost(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "id", defaultValue = "") String id,
                          @RequestParam(name = "page", defaultValue = "") String page,
                          @RequestParam(name = "title", defaultValue = "") String title,
                          @RequestParam(name = "content", defaultValue = "") String text) throws
            SQLException, IOException,
            NotImplementedException {
        UserVo userVo = Variable.getUserVo(request);
        BoardWriteVo boardWriteVo = new BoardWriteVo(id, title, text);
        BoardWriteResult boardWriteResult = this.boardService.write(userVo, boardWriteVo);

        switch (boardWriteResult) {
            case NO_MATCHING_ID:
                response.sendRedirect(String.format("board?id=%s&page=%s&result=no_matching_id", id, page));
            case NOT_ALLOWED:
                response.sendRedirect(String.format("/board?id=%s&page=%s&result=not_allowed", id, page));
                break;
            case SUCCESS:
                response.sendRedirect(String.format("/board?id=%s", id));
                break;
            default:
                // NotImplementedException : 구현되지 않은 예외
                throw new NotImplementedException();
        }

    }
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String readGet(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "article_id", defaultValue = "") String articleId,
                          @RequestParam(name = "id", defaultValue = "") String id,
                          @RequestParam(name = "page", defaultValue = "1") String page) throws
            SQLException {
        BoardReadVo boardReadVo = new BoardReadVo(articleId, id, page);
        BoardReadResponseVo boardReadResponseVo = this.boardService.read(Variable.getUserVo(request), boardReadVo);
        request.setAttribute("BoardReadResponseVo", boardReadResponseVo);
        return "bbs/read";
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public void readPost(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(name = "article_id", defaultValue = "") String articleId,
                         @RequestParam(name = "id", defaultValue = "") String id,
                         @RequestParam(name = "page", defaultValue = "1") String page,
                         @RequestParam(name = "what", defaultValue = "") String what,
                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                         @RequestParam(name = "text", defaultValue = "") String text) throws
            SQLException, IOException {
        CommentVo commentVo = new CommentVo(articleId, text);
        Object userVoObject = request.getSession().getAttribute("UserVo");
        UserVo userVo;
        if (userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        } else {
            userVo = Variable.getAnonymousUserVo();
        }
        this.boardService.writeComment(userVo, commentVo);

        if (what.equals("") || keyword.equals("")) {
            response.sendRedirect(String.format("/read?article_id=%s&id=%s&page=%s",
                    articleId,
                    id,
                    page));
        } else {
            response.sendRedirect(String.format("/read?article_id=%s&id=%s&page=%s&what=%s&keyword=%s",
                    articleId,
                    id,
                    page,
                    what,
                    keyword));
        }
    }

    @RequestMapping(value = "/read", method = RequestMethod.DELETE)
    public void readDelete(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(name = "id", defaultValue = "fre") String id,
                           @RequestParam(name = "page", defaultValue = "1") String page,
                           @RequestParam(name = "article_id", defaultValue = "") String articleId) throws
            SQLException, IOException {
        UserVo userVo = Variable.getUserVo(request);
        if (userVo == null)
            userVo = Variable.getAnonymousUserVo();

        int articleIdNum;
        try {
            articleIdNum = Integer.parseInt(articleId);
        } catch (NumberFormatException ignored) {
            articleIdNum = -1;
        }

        this.boardService.deleteArticle(userVo, articleIdNum);
        response.sendRedirect("/board?id=" + id + "&page=" + page);
    }

    @RequestMapping(value = "upload_image", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "upload") MultipartFile image,
                            @RequestParam(name = "ckCsrfToken", defaultValue = "") String token) throws
            SQLException, IOException, JSONException {
        String imageData = Converter.imageToString(image);
        int index = this.boardService.uploadImage(imageData);
        JSONObject jsonObject = new JSONObject();
        if (index > 0) {
            jsonObject.put("uploaded", true);
            jsonObject.put("url", String.format("/download_image?id=%d", index));
        } else {
            jsonObject.put("uploaded", false);
        }
        response.getWriter().print(jsonObject.toString());

    }

    @RequestMapping(
            value = "download_image",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] downloadImage(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "id", defaultValue = "") String idText) throws
            SQLException, IOException {
        int index = Converter.stringToInt(idText, -1);
        if (index > 0) {
            return this.boardService.downloadImage(index);
        } else {
            return null;
        }
    }
//    @ExceptionHandler
//    public void hendleException(HttpServletResponse response, Exception exception) {
//        try {
//            response.getWriter().print(exception.getMessage());
//        } catch (Exception ignored) {
//        }
    }

