package cn.lizhaoloveit.domain;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-06-24
 * Time:   13:12
 */
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class User {
    Person person1;
    Integer age = 18;
    String name = "lizhao";
    BigDecimal ssMoney = new BigDecimal("0.0");
}
