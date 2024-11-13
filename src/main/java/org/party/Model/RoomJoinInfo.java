package org.party.Model;

import lombok.Data;

/**
 * Description:
 * Author: chentao
 * Date: 12 11月 2024
 *
 * @version 1.0
 */
@Data
public class RoomJoinInfo {
    private String id;

    private String room_name;

    private String room_password;

    private Integer room_type;
}
