package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdSupport implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder support = new EmbedBuilder();
        support.setColor(Color.cyan);
        support.setAuthor(event.getAuthor().getName(),null, event.getAuthor().getAvatarUrl());
        support.setTitle("Support anfrage");
        support.setDescription("Dieser User Braucht support");
        support.addField("Channel",event.getTextChannel().getAsMention(),false);
        Message message = event.getGuild().getTextChannelsByName("support",false).get(0).sendMessage(support.build()).complete();
        message.addReaction(STATIC.OK).complete();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }

    @Override
    public String help() {
        return null;
    }
}
