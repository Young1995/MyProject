package org.svtcc.online.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {
    private static List<String> data;

    static {
        data = new ArrayList<String>();
        for (int i = 1; i < 5; i++) {
            data.add("Text " + i);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("test");
        view.addObject("data", data);
        return view;
    }
}
