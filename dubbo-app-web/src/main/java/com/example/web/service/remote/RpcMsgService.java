package com.example.web.service.remote;

import com.example.api.message.MessageService;
import com.example.common.dto.PageResp;
import com.example.common.dto.message.MsgDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class RpcMsgService {
    @DubboReference(version = "1.0.0")
    private MessageService messageService;

    public MsgDTO send(MsgDTO msgDTO) {
        return messageService.send(msgDTO);
    }

    public PageResp<MsgDTO> getMsgList(Integer page, Integer size, Integer uid) {
        return messageService.getMsgList(uid, page, size);
    }

}
