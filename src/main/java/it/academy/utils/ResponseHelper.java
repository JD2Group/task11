package it.academy.utils;

import it.academy.dto.response.RegistrationResponse;
import it.academy.dto.response.StudentDTOResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseHelper {

    public static StudentDTOResponse getStudentResponse(int httpStatus, String message) {
        return StudentDTOResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
    public static RegistrationResponse getRegistrationResponse(String email, String message) {
        return RegistrationResponse.builder()
                .email(email)
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
