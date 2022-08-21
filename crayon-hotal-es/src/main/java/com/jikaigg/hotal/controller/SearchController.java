package com.jikaigg.hotal.controller;

import com.jikaigg.hotal.pojo.PageResult;
import com.jikaigg.hotal.pojo.RequestParam;
import com.jikaigg.hotal.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hotel")
public class SearchController {
    @Autowired
    private IHotelService iHotelService;

    @PostMapping("/list")
    public PageResult searchList(@RequestBody RequestParam requestParam) throws IOException {
        log.info("requestParam is : {}",requestParam.toString());

        return iHotelService.search(requestParam);
    }

    @PostMapping("/filters")
    public Map<String, List<String>> searchFilters(@RequestBody RequestParam requestParam) throws IOException {
        log.info("requestParam is : {}",requestParam.toString());

        return iHotelService.filters(requestParam);
    }
}
