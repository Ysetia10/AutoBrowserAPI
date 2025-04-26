package com.example.browserStack.resource;


import com.example.browserStack.service.BrowserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class BrowserResource {
    BrowserService browserService = new BrowserService();

    public BrowserResource() {

    }

    @GetMapping("/start")
    public String start(@RequestParam String url, @RequestParam String appName){
        return browserService.openUrl(url,appName);
    }

    @GetMapping("/stop")
    public String stop(@RequestParam String appName){
        return browserService.stopApp(appName);
    }

    @GetMapping("/getUrl")
    public String getUrl(@RequestParam String appName) {
        return browserService.getUrl(appName);
    }

    @GetMapping("/cleanup")
    public String cleanup(@RequestParam String appName) {
        return browserService.cleanup(appName);
    }




}
