package org.party.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.party.PO.RoomDetailPO;
import org.party.PO.RoomPlayerPO;

/**
 * Author: chentao
 * Date: 13 11月 2024
 * 
 * @version 1.0
 */ 
@Mapper
public interface RoomPlayerMapper extends BaseMapper<RoomPlayerPO> {
}
