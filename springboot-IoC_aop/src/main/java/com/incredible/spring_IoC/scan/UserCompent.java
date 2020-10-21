package com.incredible.spring_IoC.scan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component("userCompent")
public class UserCompent {

    /**
     * 这里通过@Value注解默认值，Long类型的赋值无需带上L，只需写上数字即可，否则被判定为字符串，会出现类型异常
     */
    @Value("11")
    private Long id;
    @Value("incredible")
    private String name;
    @Value("no matter the end while be ")
    private String note;

}
