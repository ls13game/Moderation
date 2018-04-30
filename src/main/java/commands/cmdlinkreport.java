package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class cmdlinkreport implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder error = new EmbedBuilder().setColor(Color.RED).setTitle("Error");
        EmbedBuilder succes = new EmbedBuilder().setColor(Color.GREEN).setTitle("Erfolgreich");
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)){
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)){
                error.setDescription("Bitte gebe mir Rechte channels zu editieren");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if (args.length <0){
                error.setDescription("Bitte Wähle");
                error.addField("off","Zum ausschalten",false);
                error.addField("on","Zum anschalten",false);
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            }
            switch (args[0]){
                case "off":
                    if (event.getGuild().getTextChannelsByName("reports",false).size() <0) {
                        error.setDescription("Es gibt keinen Channel Names reports d.h Das Report system ist abgeschaltet");
                        event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                        break;
                    }
                    event.getGuild().getTextChannelsByName("reports",false).get(0).getManager().setTopic("Link Report: OFF");
                    succes.setDescription("Erfolgreich den Link Report ausgeschaltet");
                    event.getTextChannel().sendMessage(succes.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                    break;
                case "on":
                    Guild guild = event.getGuild();
                    GuildController controller = guild.getController();
                    if (event.getGuild().getTextChannelsByName("reports",false).size() <0){
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {

                                controller.createCategory("Moderation").queue(cat -> {

                                    controller.modifyCategoryPositions()
                                            .selectPosition(cat.getPosition())
                                            .moveTo(0).queue();

                                    String[] list = {"Reports",};

                                    Arrays.stream(list).forEach(s ->
                                            controller.createTextChannel(s).queue(chan -> chan.getManager().setParent((Category) cat).queue())
                                    );
                                });

                            }
                        }, 500);
                            break;
                        }
                        event.getGuild().getTextChannelsByName("reports",false).get(0).getManager().setTopic("Link Report: ON").complete();
                    break;
                    }
            }

    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `linkreport` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
