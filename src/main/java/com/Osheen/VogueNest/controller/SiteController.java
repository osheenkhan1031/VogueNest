package com.Osheen.VogueNest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * SiteController - VogueNest
 * Controls basic static paths like landing/home page.
 */
@Controller
public class SiteController {

    @GetMapping("/")
    public String showLandingPage() {
        return "landingpage";
    }
}