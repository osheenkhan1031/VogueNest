package com.Osheen.VogueNest.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SiteController {
    @GetMapping("/")
    public String viewLandingPage(Model model) {
        model.addAttribute("storeName", "VOGUENEST");
        return "landingpage";
    }
}
