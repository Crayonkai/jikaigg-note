package com.crayon.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crayon.redis.dto.Result;
import com.crayon.redis.entity.Voucher;

/**
 * <p>
 *  服务类
 * </p>
 *
 *  
 * @since 2021-12-22
 */
public interface IVoucherService extends IService<Voucher> {

    Result queryVoucherOfShop(Long shopId);

    void addSeckillVoucher(Voucher voucher);
}
