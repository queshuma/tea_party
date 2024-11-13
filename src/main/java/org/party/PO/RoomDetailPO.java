package org.party.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.party.Common.Enum.BooleanEnum;

/**
 * Description:
 * Author: chentao
 * Date: 12 11月 2024
 *
 * @version 1.0
 */
@Data
@TableName("room_detail")
public class RoomDetailPO {
    private Long id;

    private String roomName;

    private String roomPassword;

    private Integer roomType;

    private Integer playerMax;

    private BooleanEnum deleted;
}
