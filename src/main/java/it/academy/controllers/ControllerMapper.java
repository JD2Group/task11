package it.academy.controllers;

import it.academy.annotations.ControllerMapping;
import it.academy.annotations.GetMapping;
import it.academy.annotations.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerMapper {

    public static void sendToController(HttpServletRequest request, HttpServletResponse response)
            throws IOException, InvocationTargetException, IllegalAccessException, ServletException {

        String path = request.getPathInfo();
        if (path == null || path.split("/").length < 3) {
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Class<?> controller = getController(path);
        if (controller == null) {
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        sendToControllerMethod(request, response, controller, "/" + path.split("/")[2]);
    }

    private static Class<?> getController(String path) {
        String controllerPath = "/" + path.split("/")[1];
        String studentUrlPattern = StudentController.class.getAnnotation(ControllerMapping.class).mappingUrl();
        String mainUrlPattern = MainController.class.getAnnotation(ControllerMapping.class).mappingUrl();
        Class<?> controller = null;
        if (Objects.equals(controllerPath, studentUrlPattern)) {
            controller = StudentController.class;
        } else if (Objects.equals(controllerPath, mainUrlPattern)) {
            controller = MainController.class;
        }
        return controller;
    }

    private static void sendToControllerMethod(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      Class<?> controller,
                                                                      String path
    ) throws IOException, InvocationTargetException, IllegalAccessException {
        String httpMethod = request.getMethod();
        List<Method> mtd = getMethods(controller, path, httpMethod);

        if (mtd == null || mtd.isEmpty()) {
            response.sendError(405, "Unsupported HTTP method.");
            return;
        }

        Method method = mtd.get(0);
        method.invoke(null, request, response);
    }

    private static List<Method> getMethods(Class<?> controller, String path, String httpMethod) {
        Class<? extends Annotation> methodAnnotation = (Objects.equals(httpMethod, "POST")) ? PostMapping.class :
                (Objects.equals(httpMethod, "GET")) ? GetMapping.class : null;
        if (methodAnnotation == null) {
            return null;
        }
        Method[] methods = controller.getMethods();
        return Arrays.stream(methods)
                .filter(m -> checkAnnotation(m, methodAnnotation, httpMethod, path))
                .collect(Collectors.toList());
    }

    private static Boolean checkAnnotation(Method method, Class<? extends Annotation> methodAnnotation,
                                           String httpMethod, String path) {
        Annotation annotation = method.getAnnotation(methodAnnotation);
        if (annotation != null) {
            if (httpMethod.equals("POST")) {
                PostMapping postMapping = (PostMapping) annotation;
                return Objects.equals(postMapping.url(), path);
            }
            if (httpMethod.equals("GET")) {
                GetMapping getMapping = (GetMapping) annotation;
                return Objects.equals(getMapping.url(), path);
            }
        }
        return false;
    }
}
