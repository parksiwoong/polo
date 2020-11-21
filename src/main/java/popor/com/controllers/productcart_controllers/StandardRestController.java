package popor.com.controllers.productcart_controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public abstract class StandardRestController {
    @ExceptionHandler({Exception.class})
    protected void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("서비스 이용에 불편을 드려서 죄송합니다.");
            exception.printStackTrace();
        } catch (Exception ignored) {}
    }
}