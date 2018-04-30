package commands;

import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class cmdClear implements Command {
    EmbedBuilder error = new EmbedBuilder().setColor(Color.RED);
    private int getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            int numb = getInt(args[0]);
            if (args.length < 1) {
                event.getTextChannel().sendMessage(
                        error.setDescription("Please enter a number of messages you want to delete!").build()
                ).queue();
            }

            if (numb > 1 && numb <= 100) {

                try {

                    MessageHistory history = new MessageHistory(event.getTextChannel());
                    List<Message> msgs;

                    event.getMessage().delete().queue();

                    msgs = history.retrievePast(numb).complete();
                    event.getTextChannel().deleteMessages(msgs).queue();

                    Message msg = event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.GREEN).setDescription("Deleted " + args[0] + " messages!").build()
                    ).complete();

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            msg.delete().queue();
                        }
                    }, 3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                event.getTextChannel().sendMessage(
                        error.setDescription("please enter a number of messages between 2 and 100!").build()
                ).queue();
            }
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `clear` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
