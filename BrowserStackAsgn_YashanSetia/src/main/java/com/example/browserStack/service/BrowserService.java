package com.example.browserStack.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class BrowserService {


    public String openUrl(String url,String app) {
        try {
            if(app.equalsIgnoreCase("Chrome")){
                new ProcessBuilder("open", "-a", "google chrome", url).start();
            }
            else if(app.equalsIgnoreCase("firefox")){
                new ProcessBuilder("open", "-a", "firefox", url).start();
            }
            else{
                return "Browser name is incorrect";
            }
            return "Opened " + url + "on "+app;
        }
        catch(IOException e){
            return "Unable to open "+url+" on "+app+". Exception Occurred: "+ e;
        }
    }

    public String stopApp(String app) {
        try {
            if(app.equalsIgnoreCase("Chrome")){
                String[] args = {"osascript", "-e", "quit app \"Google Chrome\""};
                new ProcessBuilder(args).start();
            }
            else if(app.equalsIgnoreCase("firefox")){
                String[] args = {"osascript", "-e", "quit app \"firefox\""};
                new ProcessBuilder(args).start();
            }
            else{
                return "Browser name is incorrect";
            }

            return "closed"+ app;
        }
        catch (IOException e){
            return "Unable to close application with exception"+ e;
        }
    }

    public String getUrl(String app){
        try{
            if(app.equalsIgnoreCase("Chrome")){
                String[] args = {"osascript", "-e", "tell application \"google chrome\" to get URL of tab 1 of window 1"};
                Process proc = new ProcessBuilder(args).start();
                String output;
                try(BufferedReader reader= new BufferedReader(
                        new InputStreamReader(proc.getInputStream()))){
                    output = reader.lines().collect(Collectors.joining("\n"));
                }
                return "URL fetched: " + output;
            }
            //todo
            else if(app.equalsIgnoreCase("firefox")){
                String[] args = {"osascript", "-e", "tell application \"firefox\" to get URL of tab 1 of window 1"};
                Process proc = new ProcessBuilder(args).start();
                String output;
                try(BufferedReader reader= new BufferedReader(
                        new InputStreamReader(proc.getInputStream()))){
                    output = reader.lines().collect(Collectors.joining("\n"));
                }
                return "URL fetched: " + output;
            }
            else{
                return "Browser name is incorrect";
            }
        }
        catch(IOException e){
            return "Unable to fetch Url with exception"+ e;
        }
    }

    public String cleanup(String app){
        try{
            if (app.equalsIgnoreCase("chrome")) {
                String userProfile = System.getenv("HOME") + "/Library/Application Support/Google/Chrome/Default";
                new ProcessBuilder("rm", "-rf", userProfile + "/Cache/*").start();
                new ProcessBuilder("rm", "-rf", userProfile + "/History").start();
            }
            else if (app.equalsIgnoreCase("firefox")) {
                String userProfile = System.getenv("HOME") + "/Library/Application Support/Firefox/Profiles";
                new ProcessBuilder("rm", "-rf", userProfile + "/*/cache2/*").start();
                new ProcessBuilder("rm", "-rf", userProfile + "/*/places.sqlite").start();
            } else {
                return "Unsupported browser on macOS";
            }
            return "Cache Cleaned on "+app;
        }
        catch(IOException e){
            return "Unable to Clean cache with exception"+ e;
        }
    }
}
