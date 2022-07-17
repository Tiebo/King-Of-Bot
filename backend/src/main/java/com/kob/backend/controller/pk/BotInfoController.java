package com.kob.backend.controller.pk;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("getbotinfo/")
    public List<Map<String, String>> GetBotInfo() {
        List<Map<String, String>> list = new LinkedList<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("name","tiger");
        map1.put("rating","1600");
        HashMap<String, String> map2 = new HashMap<>();
        list.add(map1);
        return list;
    }
}
