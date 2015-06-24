package org.mage.network.model;

import io.netty.channel.ChannelHandlerContext;
import org.mage.network.interfaces.MageServer;

/**
 *
 * @author BetaSteward
 */
public class SendFeedbackRequest extends ServerRequest {
    private final String title;
    private final String type;
    private final String message;
    private final String email;

    public SendFeedbackRequest(String title, String type, String message, String email) {
        this.title = title;
        this.type = type;
        this.message = message;
        this.email = email;
    }

    @Override
    public void handleMessage(MageServer server, ChannelHandlerContext ctx) {
        server.sendFeedbackMessage(ctx.channel().id().asLongText(), title, type, message, email);
    }
    
}
