package com.jikaigg.hotal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jikaigg.hotal.mapper.HotelMapper;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.service.IHotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
