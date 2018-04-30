package commands;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class cmdstop implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder succes = new EmbedBuilder();
        EmbedBuilder error = new EmbedBuilder();
        event.getMessage().delete().queue();
        if (Dev.isDev(event.getAuthor())){
            succes.setColor(Color.RED);
            succes.setTitle("\uD83D\uDD0C Shutdown...");
            event.getTextChannel().sendMessage(succes.build()).queue();
            event.getJDA().shutdown();

        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
    }


    public String help() {
        return null;
    }
}
