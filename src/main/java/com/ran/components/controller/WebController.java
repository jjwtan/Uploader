package com.ran.components.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ran on 2/12/2017.
 */
@Controller
public class WebController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/viewFiles")
    public String viewFiles() {
        return "viewFiles";
    }
}
