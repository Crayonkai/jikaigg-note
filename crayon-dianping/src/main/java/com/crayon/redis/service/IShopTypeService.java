package com.crayon.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crayon.redis.entity.ShopType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 *  
 * @since 2021-12-22
 */
public interface IShopTypeService extends IService<ShopType> {

    List<ShopType> queryList();
}
