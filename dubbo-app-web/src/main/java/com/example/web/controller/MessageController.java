package com.example.web.controller;

import com.example.common.dto.PageResp;
import com.example.common.dto.Result;
import com.example.common.dto.message.MsgDTO;
import com.example.web.service.remote.RpcMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {

    private final RpcMsgService rpcMsgService;

    public MessageController(RpcMsgService rpcMsgService) {
        this.rpcMsgService = rpcMsgService;
    }

    @PostMapping("/send")
    public Result<?> send(@RequestBody MsgDTO msgDTO) {
        try {
            MsgDTO resp = rpcMsgService.send(msgDTO);
            return Result.wrapOK(resp);
        } catch (Exception e) {
            log.info("send message error: {}", e.getMessage());
            return Result.wrapErr("网络繁忙， 请稍后再试");
        }
    }

    @GetMapping("/list")
    public Result<PageResp<MsgDTO>> getMsgList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "uid", defaultValue = "0") Integer uid
    ) {
        PageResp<MsgDTO> msgList = new PageResp<>();
        try {
            msgList = rpcMsgService.getMsgList(page, size, uid);
            return Result.wrapOK(msgList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get msg list failure err:{}", e.getMessage());
        }
        return Result.wrapOK(msgList);
    }
}
