package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<html>" +
                "<head>" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "    <link rel=\"stylesheet\" href=\"css/style.css\">" +
                "</head>" +
                "<body>" +
                "<section>" +
                "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">" +
                "    <tr>" +
                "        <th>UUID</th>" +
                "        <th>NAME</th>" +
                "    </tr>");

        for (Resume r : Config.get().getStorage().getAllSorted()) {
            writer.println("<tr>");
            writer.println("<td>" + r.getUuid() + "</td>");
            writer.println("<td>" + r.getFullName() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>" + "/section" + "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
