package commands;

import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmddelete implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            if (args.length < 1) {
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte gebe eine ID an");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
                return;
            }
            event.getTextChannel().getMessageById(args[0]).complete().delete().queue();
            success.setColor(Color.GREEN);
            success.setTitle("Erfolgreich");
            success.setDescription("Nachricht erfolgreich gelöscht");
            event.getTextChannel().sendMessage(success.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
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
