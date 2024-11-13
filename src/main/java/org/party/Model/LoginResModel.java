package org.party.Model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Author: chentao
 * Date: 02 10月 2024
 *
 * @version 1.0
 */
@Setter
@Getter
public class LoginResModel {
    private String session_key;
    private String openid;
}
