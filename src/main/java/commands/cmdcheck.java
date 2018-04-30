package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

import java.awt.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class cmdcheck implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder created = new EmbedBuilder();
        EmbedBuilder error = new EmbedBuilder();
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Ich habe keine rechte channels zu erstellen");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
                Guild guild = event.getGuild();
                GuildController controller = guild.getController();

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {

                        controller.createCategory("Moderation").queue(cat -> {

                            controller.modifyCategoryPositions()
                                    .selectPosition(cat.getPosition())
                                    .moveTo(0).queue();

                            String[] list = {"reports", "support"};

                            Arrays.stream(list).forEach(s ->
                                    controller.createTextChannel(s).queue(chan -> chan.getManager().setParent((Category) cat).queue())
                            );
                        });

                    }
                }, 500);
                created.setColor(Color.GREEN);
                created.setTitle("Erfolgreich");
                created.setDescription("Erfolgreich Meine benötigten Channels erstellt");
                event.getTextChannel().sendMessage(created.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
        }else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Du hast dafür keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            return;
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
