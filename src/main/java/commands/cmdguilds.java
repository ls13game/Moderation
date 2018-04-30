package commands;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdguilds implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public void action(String[] args, MessageReceivedEvent event) {
        if (Dev.isDev(event.getAuthor())){
            StringBuilder out = new StringBuilder();
            out.append("\nThis bot is running on " + event.getJDA().getGuilds().size()+ " servers \n\n");

            for (Guild g : event.getJDA().getGuilds() ) {
                out.append(g.getName() + " \n "+"ID:"+g.getId()+"  \n   Owner: <@" + g.getOwner().getUser().getId() + "> \n\n");
            }
            event.getAuthor().openPrivateChannel().complete().sendMessage(out).queue();
        }
        }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
