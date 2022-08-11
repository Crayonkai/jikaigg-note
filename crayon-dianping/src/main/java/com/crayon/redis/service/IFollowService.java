package com.crayon.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crayon.redis.dto.Result;
import com.crayon.redis.entity.Follow;

/**
 * <p>
 *  服务类
 * </p>
 *
 *  
 * @since 2021-12-22
 */
public interface IFollowService extends IService<Follow> {

    Result follow(Long followUserId, Boolean isFollow);

    Result isFollow(Long followUserId);

    Result followCommons(Long id);
}
