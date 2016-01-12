package com.ndovado.webapp.shared;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    static public Cookie findCookie(HttpServletRequest request,
            String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    static public String findCookieValue(HttpServletRequest request,
            String name) {
        Cookie cookie = findCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }
}  
