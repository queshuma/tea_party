package org.party.Common.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: chentao
 * Date: 12 11月 2024
 *
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {
    TRUE(1, "是"),
    FALSE(0, "否");

    @EnumValue // 标记数据库存的值是code
    private final int key;

    private final String value;
}
