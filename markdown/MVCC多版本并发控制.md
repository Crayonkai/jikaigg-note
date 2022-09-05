多版本数据链存储在undo日志(回滚日志)中。





当前读：

select ... lock in share mode

select for update

insert

 update

 delete



快照读   

Read view读视图

rw_trx_ids: 生成Read view时，当前活跃的事务id组

min_trx_id: rw_trx_ids中最小的事务id。

max_trx_id: 生成Read view时，将要分配给下一个事务的id

curr_trx_id: 创建Read view的当前事务id。