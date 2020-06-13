package cn.lizhaoloveit.springmvc.servlet;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-13
 * Time:   14:04
 */
public abstract class AbstractHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public abstract void doDispatch(HttpServletRequest request, HttpServletResponse response);
}
