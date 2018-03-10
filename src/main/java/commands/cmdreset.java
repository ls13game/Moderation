package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdreset implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals(STATIC.DEV)){
            event.getJDA().getPresence().setGame(Game.playing(STATIC.GAME));
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
