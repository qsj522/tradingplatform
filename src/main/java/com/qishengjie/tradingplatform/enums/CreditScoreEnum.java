package com.qishengjie.tradingplatform.enums;

/**
 * @Package: com.qishengjie.tradingplatform.enums
 * @ClassName: CreditScoreEnum
 * @Author: SamSung
 * @CreateTime: 2021-04-11 9:55
 * @Description:
 */
public enum CreditScoreEnum {
    POOR(60,"信用极差"),
    BAD(80,"信用较差"),
    NORMAL(100,"信用一般"),
    GOOD(120,"信用较好"),
    EXCELLENT(140,"信用极好");

    private int value;
    private String desc;

    CreditScoreEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
