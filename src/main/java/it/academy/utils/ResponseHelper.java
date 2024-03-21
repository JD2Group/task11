package it.academy.utils;

import it.academy.dto.response.StudentDTOResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static it.academy.utils.Constants.GSON;

public class ResponseHelper {

    public static StudentDTOResponse getStudentResponse(int httpStatus, String message) {
        return StudentDTOResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

    public static void sendJsonResponse(HttpServletResponse response, String out) throws IOException {
        PrintWriter outPrinter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        outPrinter.print(out);
        outPrinter.flush();
    }

}
