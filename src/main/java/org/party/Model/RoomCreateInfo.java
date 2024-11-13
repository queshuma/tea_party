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
public class RoomCreateInfo {
    private String room_name;

    private String room_password;

    private Integer room_type;

    private Integer player_max;
}
