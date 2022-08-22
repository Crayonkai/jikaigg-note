package com.jikaigg.hotal.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jikaigg.hotal.constants.MqConstants;
import com.jikaigg.hotal.pojo.Hotel;
import com.jikaigg.hotal.pojo.PageResult;
import com.jikaigg.hotal.service.IHotelService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    @GetMapping("/list")
    public PageResult hotelList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer size
    ) {
        Page<Hotel> result = hotelService.page(new Page<>(page, size));
        long total = result.getTotal();
        List<Hotel> records = result.getRecords();

        return new PageResult(total, records);
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel) {
        hotelService.save(hotel);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE,MqConstants.HOTEL_INSERT_KEY,hotel.getId());
    }

    @PutMapping()
    public void updateById(@RequestBody Hotel hotel) {
        if (hotel.getId() == null) {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE,MqConstants.HOTEL_INSERT_KEY,hotel.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        hotelService.removeById(id);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE,MqConstants.HOTEL_DELETE_KEY,id);

    }
}
