package com.wspa.localmoviehub.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    String cur_city = "Unknown";

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("userCity", getCity(request));
        return "main";
    }

    @GetMapping("/explore")
    public String explore(Model model, HttpServletRequest request) {
        model.addAttribute("userCity", getCity(request));
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

}
