package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.model.MessagesDO;
import org.apache.ibatis.annotations.Param;

/**
 * created 5/7/2021 1:01 PM
 *
 * @author luowen <loovien@163.com>
 */
public interface MessageRepository extends BaseMapper<MessagesDO> {

    IPage<MessagesDO> selectMsgListByUid(Page<MessagesDO> page, @Param("uid") Integer uid);
}
