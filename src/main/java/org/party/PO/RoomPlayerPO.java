package org.party.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.party.Common.Enum.BooleanEnum;

/**
 * Description:
 * Author: chentao
 * Date: 13 11月 2024
 *
 * @version 1.0
 */
@Data
@TableName("room_player")
public class RoomPlayerPO {
    private Long id;
    private Long roomId;
    private String playerId;
    private String joinTime;
    private BooleanEnum deleted;
}
