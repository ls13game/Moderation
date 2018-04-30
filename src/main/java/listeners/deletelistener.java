/*
package listeners;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class deletelistener extends ListenerAdapter {
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        EmbedBuilder error = new EmbedBuilder().setColor(Color.RED).setTitle("ERROR");
        String Message = event.getMessageId();
        String Channel = event.getTextChannel().getId();
        if (!event.getMember().getUser().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (event.getReactionEmote().getName().equals("trash")) {
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        event.getGuild().getTextChannelById(Channel).getMessageById(Message).complete().delete().complete();
                        return;
                    }else {
                        error.setDescription("Ich habe keine rechte Nachrichten zu löschen");
                        event.getTextChannel().sendMessage(event.getMember().getAsMention()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                        event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                    }
                }
                    }
                }
                if(!event.getUser().getId().equals(event.getJDA().getSelfUser().getId())){
            if(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)){
                if(event.getReactionEmote().getId().equals(STATIC.mok)){
                    event.getGuild().getTextChannelById(Channel).getMessageById(Message).complete().clearReactions().queue();
                }
            }
                }
            }
        }

*/
