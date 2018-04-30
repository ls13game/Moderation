package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdinfo implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent e) {
        Message message = e.getChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(Color.CYAN)
                        .setAuthor(e.getJDA().getSelfUser().getName(), null, e.getJDA().getSelfUser().getAvatarUrl())
                        .setDescription("Ich bin der Moderationsbot\n" +
                                "**Help**\n" +
                                STATIC.PREFIX + "help\n" +
                                "**[INVITE](https://discordapp.com/oauth2/authorize?client_id=415154495039864834&permissions=2146958583&scope=bot)\n" +
                                "[UPVOTE](https://discordbots.org/bot/415154495039864834)**\n" +
                                "**[SUPPORT SERVER](https://discord.gg/4nnf8qE)**")
                        .setFooter("Coded by ls13game®#8562 & oskar#7402", e.getJDA().getSelfUser().getAvatarUrl())
                        .build()
        ).complete();
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
