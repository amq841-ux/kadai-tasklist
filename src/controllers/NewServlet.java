package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        Task t = new Task();

        String content = "hello";
        t.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        em.persist(t);
        em.getTransaction().commit();

        response.getWriter().append(Integer.valueOf(t.getId()).toString());

        /*
         * // CSRF対策 request.setAttribute("_token",
         * request.getSession().getId());
         *
         * // インスタンス作成 request.setAttribute("task", new Task());
         *
         * RequestDispatcher rd =
         * request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
         * rd.forward(request, response);
         */
    }
}
