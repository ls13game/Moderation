package commands;

import UTIL.SECRETS;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.discordbots.api.client.DiscordBotListAPI;
import org.discordbots.api.client.entity.SimpleUser;


public class cmdvotet implements Command {
    DiscordBotListAPI api = new DiscordBotListAPI.Builder()
            .token(SECRETS.DTOKEN)
            .build();
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getId().equals("235399089796284416")){
            SimpleUser voters = api.getVoters("415154495039864834");
            if (event.getAuthor().getId().contains((CharSequence) voters)){
                event.getGuild().getController().addRolesToMember(event.getMember(),event.getGuild().getRolesByName("Votet",false).get(0)).queue();
                event.getTextChannel().sendMessage("Ich habe dir die rolle Votet gegeben").queue();
            }
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
