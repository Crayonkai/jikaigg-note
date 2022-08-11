package com.crayon.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crayon.redis.dto.Result;
import com.crayon.redis.entity.Shop;

/**
 * <p>
 *  服务类
 * </p>
 *
 *  
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    Result queryById(Long id);

    Result update(Shop shop);

    Result queryShopByType(Integer typeId, Integer current, Double x, Double y);
}
