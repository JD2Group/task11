package it.academy.servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Objects;

@WebListener
public class Listener implements HttpSessionAttributeListener {

    public static final String PASSWORD = "password";

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        if (Objects.equals(attrName, PASSWORD)) {
            System.out.println("Иванушка дурачок опять передает пароль в атрибутах: " + event.getValue());
        }
        System.out.println(attrName + " атрибут был получен из сессии");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        if (Objects.equals(attrName, PASSWORD)) {
            System.out.println("Иванушка дурачок удалил пароль из сессии: " + event.getValue());
        }
        System.out.println(attrName + " атрибут был удален из сессии");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        if (Objects.equals(attrName, PASSWORD)) {
            System.out.println("Иванушка дурачок изменил атрибут:  " + event.getValue());
        }
        System.out.println(attrName + " атрибут сессии был изменен.");
    }
}
