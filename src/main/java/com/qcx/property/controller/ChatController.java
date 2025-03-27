package com.qcx.property.controller;

import com.qcx.property.annotation.AuthCheck;
import com.qcx.property.domain.dto.chat.AiMessageDTO;
import com.qcx.property.service.AiChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "AI 管理")
@RestController
public class ChatController {

    @Resource
    private AiChatService aiChatService;

    @AuthCheck(code = "AI_CHAT_AGENT")
    @PostMapping(value = "/chatAgent", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "智能客服聊天接口" )
    public Flux<ServerSentEvent<String>> chatStreamWithHistory(@RequestBody AiMessageDTO aiMessageDTO) {
        return aiChatService.chatWithAgent(aiMessageDTO);
    }
}