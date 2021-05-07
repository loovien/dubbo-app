package com.example.api.message;

import com.example.common.dto.PageResp;
import com.example.common.dto.message.MsgDTO;

public interface MessageService {
    MsgDTO send(MsgDTO msgDTO);

    PageResp<MsgDTO> getMsgList(Integer uid, Integer page, Integer size);
}
