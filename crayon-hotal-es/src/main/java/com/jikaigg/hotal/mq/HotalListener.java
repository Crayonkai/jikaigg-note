package com.jikaigg.hotal.mq;

import com.jikaigg.hotal.constants.MqConstants;
import com.jikaigg.hotal.service.IHotelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HotalListener {

    @Autowired
    private IHotelService hotelService;

    /*
    * 监听酒店新增或修改的业务
    * */
    @RabbitListener(queues = MqConstants.HOTEL_INSERT_QUEUE)
    public void listenHotalInsertOrUpdate(Long id){
        hotelService.insertById(id);
    }

    /*
     * 监听酒店新增或修改的业务
     * */
    @RabbitListener(queues = MqConstants.HOTEL_DELETE_QUEUE)
    public void listenHotalDelete(Long id){
        hotelService.deleteById(id);
    }
}
