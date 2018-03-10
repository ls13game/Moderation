package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdinfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent e) {
        Message message = e.getChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(Color.CYAN)
                        .setAuthor(e.getJDA().getSelfUser().getName(), null, e.getJDA().getSelfUser().getAvatarUrl())
                        .setDescription("Ich bin der Moderationsbot\n" +
                                "**Help**\n" +
                                STATIC.PREFIX + "help\n" +
                                "**[INVITE](https://discordapp.com/api/oauth2/authorize?client_id=415154495039864834&permissions=263715926&scope=bot)\n" +
                                "[UPVOTE](https://discordbots.org/bot/415154495039864834)**\n" +
                                "**[SUPPORT SERVER](https://discord.gg/4nnf8qE)**")
                        .setFooter("Coded by ls13game®#8562", "https://cdn.discordapp.com/avatars/235395943619493888/20dd0409d45d90ddf394b3287633373e.png")
                        .build()
        ).complete();
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
