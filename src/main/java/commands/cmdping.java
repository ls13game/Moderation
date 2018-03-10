package commands;

import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class cmdping implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder sucess = new EmbedBuilder();
        sucess.setTitle("Mein Ping");
        sucess.setColor(Color.GREEN);
        sucess.setDescription(event.getJDA().getPing()+"ms");
        event.getTextChannel().sendMessage(sucess.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
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
