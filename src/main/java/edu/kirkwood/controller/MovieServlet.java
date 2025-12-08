package edu.kirkwood.controller;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value="/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        req.setAttribute("search",search);
        List<Movie> searchResults = null;
        if (search != null && !search.isEmpty()) {
            try {
                searchResults = getResults(search);
                req.setAttribute("movies", searchResults);
                if (searchResults.isEmpty()) {
                    req.setAttribute("searchError", "No results found");
                }
            } catch (RuntimeException e) {
                req.setAttribute("searchError", e.getMessage());
            }
        }
        req.getRequestDispatcher("WEB-INF/movies.jsp").forward(req, resp);
    }

    public static List<Movie> getResults(String search) {
        try {
            MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
            List<Movie> movies = new ArrayList<>();

            switch (movieDAO) {
                case XmlMovieDAO xmlMovieDAO -> movies.addAll(xmlMovieDAO.search(search));
                case MySQLMovieDAO mySQLMovieDAO -> movies.addAll(mySQLMovieDAO.search(search));
                case JsonMovieDAO jsonMovieDAO -> movies.addAll(jsonMovieDAO.search(search));
                default -> {
                }
            }

            return movies;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
