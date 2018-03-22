package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oskar
 * on 19.03.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class cmdMsgOwner implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        TextChannel tc = event.getTextChannel();


         if (event.getAuthor().getId().equals(STATIC.DEV2) || event.getAuthor().getId().contains(STATIC.DEV)){
            if (args.length < 1) {
                error(tc, help());
                return;
            }
            String content = String.join(" ", args);
            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.WHITE)
                    .setTitle("\uD83D\uDC8C New Message")
                    .setDescription("")
                    .addField("Message from the developers", "\n " + content + "\n\n" + "[Comunity Server](https://discord.gg/KDDASr4)", false);

            for (Guild g : event.getJDA().getGuilds()) {
                g.getOwner().getUser().openPrivateChannel().complete().sendMessage(eb.build()).queue();
            }
        }else
            error(tc, "Missing Permissions");


    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }

    @Override
    public String help() {
        String help = STATIC.PREFIX + "MsgOwner (text)";
        return help;
    }

    public void error(TextChannel tc, String content) {
        tc.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
    }
}
