package com.platform.common.utils;

import java.util.UUID;

/**
 * Created by GaoXiang
 */
public class UUIDFactory {

    /**
     * 生成一个Long类型的id
     * @return
     */
    public static String getStringId(){

        Long random = UUID.randomUUID().getMostSignificantBits();
        Long id = Math.abs(random);
        return id.toString();
    }

    public static void main(String[] args) {

        for(int i = 0;i < 1000;i++ ){
            String id = getStringId();
            System.out.println(id);
        }
    }

}
