package com.example.gateway.tool;

/**
 * created 5/8/2021 3:46 PM
 *
 * @author luowen <loovien@163.com>
 */
final public class ConversionUtil {

    public static String getChannelId(Integer sender, Integer receiver) {
        if (sender > receiver) {
            return String.format("%d:%d", sender, receiver);
        }
        return String.format("%d:%d", receiver, sender);
    }
}
