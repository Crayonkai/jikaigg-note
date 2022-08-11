package com.crayon.redis.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crayon.redis.entity.ShopType;
import com.crayon.redis.mapper.ShopTypeMapper;
import com.crayon.redis.service.IShopTypeService;
import com.crayon.redis.utils.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @since 2021-12-22
 */
@Slf4j
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<ShopType> queryList() {
        // 1. 查redis
        String shopType = stringRedisTemplate.opsForValue().get(RedisConstants.SHOP_TYPE_LIST);

        // 2. redis存在，返回
        if (StrUtil.isNotBlank(shopType)) {
            JSONArray list = JSONUtil.parseArray(shopType);
            List<ShopType> shopTypes = JSONUtil.toList(list, ShopType.class);
            log.info("redis中存在列表：{}", shopType);
            return shopTypes;
        }

        // 3. redis不存在，查数据库
        List<ShopType> shopTypes = query().orderByAsc("sort").list();
        // 4. 将数据库记录缓存到redis
        String shopTypeJson = JSONUtil.toJsonStr(shopTypes);
        stringRedisTemplate.opsForValue().set(RedisConstants.SHOP_TYPE_LIST, shopTypeJson, RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
        log.info("redis中不存在列表，查询数据库后写入缓存成功！");
        // 5. 返回
        return shopTypes;
    }
}
