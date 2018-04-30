package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class cmdTwitter implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder twitter = new EmbedBuilder().setColor(Color.CYAN).setTitle("Mein Twitter Account").setDescription("Klick [Mich](https://twitter.com/moderationsbot)");
        event.getTextChannel().sendMessage(twitter.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES);});
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
