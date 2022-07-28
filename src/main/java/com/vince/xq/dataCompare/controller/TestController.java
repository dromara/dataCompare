package com.vince.xq.dataCompare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TestController {
    private static final Logger log= LoggerFactory.getLogger(TestController.class);


    @RequestMapping("/")
    public String index(ModelMap map){
        return "index";
    }

    @RequestMapping("/index.html")
    public String indexPage(ModelMap map){
        return "index";
    }

    @RequestMapping("/addDataBase.html")
    public String addDataBase(ModelMap map) {
        return "addDataBase";
    }

    @RequestMapping("/grids.html")
    public String grids(ModelMap map) {
        return "grids";
    }

    @RequestMapping("/tables.html")
    public String forms(ModelMap map) {
        return "tables";
    }


}
