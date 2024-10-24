package com.spar3chang3;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LogMessageController {

    @MessageMapping("/log")
    @SendTo("/topic/log")
    public LogMessage log(LogMessage newLog) {
        return new LogMessage(HtmlUtils.htmlEscape(newLog.getContent()));
    }
}
