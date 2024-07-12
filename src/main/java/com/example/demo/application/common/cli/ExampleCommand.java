package com.example.demo.application.common.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ExampleCommand {

    @ShellMethod(key = "print-hello")
    public String helloCommand(@ShellOption(defaultValue = "world") String arg
    ) {
        return "Hello " + arg;
    }
}