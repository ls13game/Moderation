package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class privateMessage extends ListenerAdapter {
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        EmbedBuilder message = new EmbedBuilder().setColor(Color.green).setTitle("Neue Private Nachricht");
        String content = String.join(" ", event.getMessage().getContentDisplay());
        message.setAuthor(event.getAuthor().getName()+event.getAuthor().getDiscriminator(),null,event.getAuthor().getAvatarUrl());
        message.setDescription(content);
        event.getJDA().getTextChannelById("436475582293999626").sendMessage(message.build()).queue();
    }

}
