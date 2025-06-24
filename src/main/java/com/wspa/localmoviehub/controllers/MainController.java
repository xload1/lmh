package com.wspa.localmoviehub.controllers;

import com.wspa.localmoviehub.entities.Movies;
import com.wspa.localmoviehub.functional.MovieService;
import com.wspa.localmoviehub.functional.TheatreService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    String cur_city = "Unknown";
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("userCity", getCity(request));
        model.addAttribute("recommendedMovies", movieService.getAllReleasedMovies().stream().sorted().limit(4).collect(Collectors.toList()));
        model.addAttribute("trendingMovies", movieService.getTrendingInCity(cur_city).stream().limit(10).collect(Collectors.toList()));
        return "main";
    }

    @GetMapping("/explore")
    public String explore(Model model, HttpServletRequest request,
                          @RequestParam(value = "genre", required = false, defaultValue = "") String genre,
                          @RequestParam(value = "minRating", required = false) Integer minRating,
                          @RequestParam(value = "maxRating", required = false) Integer maxRating,
                          @RequestParam(value = "keyword", required = false) String keyword) {


        model.addAttribute("userCity", getCity(request));
        List<Movies> movies = movieService.getFilteredMovies(genre, minRating, maxRating, keyword);
        model.addAttribute("allMovies", movies);
        model.addAttribute("chosenGenre", genre);
        model.addAttribute("nearbyTheaters", theatreService.getNearbyTheatres(cur_city));

        return "explore";
    }

    @PostMapping("/setCity")
    public String setCity(
            @RequestParam(value = "city", required = false, defaultValue = "Loading...") String city,
            Model model, HttpServletResponse response){
        // Process or store the city as needed
        System.out.println("Received city: " + city);
        cur_city = city;
        if(city.equals("Loading...")) {
            Cookie cookie = new Cookie("userCity", city);
            cookie.setMaxAge(60 * 60 * 24); // 1 day
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        model.addAttribute("userCity", cur_city);
        return "redirect:/home";
    }
    @GetMapping("/about")
    public String about(Model model, HttpServletRequest request) {
        model.addAttribute("userCity", getCity(request));
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model, HttpServletRequest request) {
        model.addAttribute("userCity", getCity(request));
        return "contact";
    }

    public String getCity(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCity")) {
                    cur_city = cookie.getValue();
                }
            }
        }
        return cur_city;
    }
    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable int id, Model model) {
        movieService.getMovieById(id).ifPresent(m -> model.addAttribute("movie", m));
        return "movie-details";
    }
}
