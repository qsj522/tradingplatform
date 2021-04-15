package com.qishengjie.tradingplatform.util;

import java.util.UUID;

/**
 * @Package: com.qishengjie.tradingplatform.util
 * @ClassName: UUIDUtils
 * @Author: SamSung
 * @CreateTime: 2021-04-11 10:28
 * @Description:
 */
public class UUIDUtils {

        public static String getUUID(){
            return UUID.randomUUID().toString().replace("-", "");
        }

}
