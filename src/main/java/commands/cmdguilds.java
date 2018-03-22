package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdguilds implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals(STATIC.DEV) || event.getAuthor().getId().contains(STATIC.DEV2)){
            String out = "\nThis bot is running on following servers: \n";

            for (Guild g : event.getJDA().getGuilds() ) {
                out += g.getName() + " (" + g.getId() + ") \n";
            }
            event.getAuthor().openPrivateChannel().complete().sendMessage(out).queue();
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
