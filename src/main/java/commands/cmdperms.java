package commands;

import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class cmdperms implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder info = new EmbedBuilder().setColor(Color.green);
        if (Dev.isDev(event.getAuthor())){
            info.setTitle("ℹMeine rechte");
            info.setDescription(event.getGuild().getSelfMember().getPermissions().toString());
            event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
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