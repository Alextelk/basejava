package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // Writer writer = response.getWriter();
        PrintWriter pw = response.getWriter();

        pw.write("<!DOCTYPE html>");
        pw.write("<html>");
        pw.write("<head>");
        pw.write("<style>");
        pw.write("table {" +
                "font-family: arial, sans-serif;" +
                "border-collapse: collapse;" +
                "width: 100%;" +
                "width: 600px;" +
                "}");

        pw.write("td, th {" +
                "border: 1px solid #dddddd;" +
                "text-align: left;" +
                "padding: 8px;" +
                "}");

        pw.write("tr:nth-child(even) {" +
                "background-color: #dddddd;" +
                "}");
        pw.write("</style>");
        pw.write("</head>");
        pw.write("<body>");

        pw.write("<h2>Список резюме</h2>");

        pw.write("<table>");
        pw.write("<tr>");
        pw.write("<th>UUID</th>");
        pw.write("<th>NAME</th>");
        pw.write("</tr>");

        for (Resume r : storage.getAllSorted()) {
            pw.write("<tr>");
            pw.write("<td>" + r.getUuid() + "</td>");
            pw.write("<td>" + r.getFullName() + "</td>");
            pw.write("</tr>");
        }

        pw.write("</table>");

        pw.write("</body>");
        pw.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
