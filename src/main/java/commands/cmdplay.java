package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdplay implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder succes = new EmbedBuilder();
        if (event.getAuthor().getId().equals(STATIC.DEV)) {
            event.getJDA().getPresence().setGame(Game.playing(String.join(" ", args)));
            event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
            succes.setTitle("Set Game Status to");
            succes.setColor(Color.GREEN);
            succes.setDescription(String.join("", args));
            event.getTextChannel().sendMessage(succes.build()).queue(msg -> {
                msg.delete().queueAfter(20, TimeUnit.SECONDS);
            });
        }else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Rechte dafür");
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
