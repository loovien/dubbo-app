package com.example.gateway.handler;

import lombok.Data;

/**
 * created 5/8/2021 3:18 PM
 *
 * @author luowen <loovien@163.com>
 */
public enum Handlers {

    CHAT_HANDLER(10, ChatHandler.class),

    USER_HANDLER(1, UserHandler.class);

    private final int command;

    private final Class<? extends Handler> handler;

    Handlers(int command, Class<? extends Handler> handlerClass) {
        this.command = command;
        this.handler = handlerClass;
    }

    public int getCommand() {
        return command;
    }

    public Class<? extends Handler> getHandler() {
        return handler;
    }
}
