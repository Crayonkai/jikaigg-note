package com.jikaigg.hotal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.pojo.PageResult;
import com.jikaigg.hotal.pojo.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParam requestParam) throws IOException;

    Map<String, List<String>> filters(RequestParam requestParam) throws IOException;
}
