package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cmdban implements Command{

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

   

    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
       
        if (event.getMember().hasPermission(Permission.BAN_MEMBERS)){
            if (args.length <1){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte wähle einen User aus");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
            }
            if(!event.getGuild().getSelfMember().canInteract(event.getMessage().getMentionedMembers().get(0))){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Ich habe keine Rechte dazu");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            String member = event.getMessage().getMentionedMembers().get(0).getUser().getId();
            String args0 = args[0];
            event.getGuild().getMemberById(member).getUser().openPrivateChannel().queue((channel) -> {
                channel.sendMessage(new EmbedBuilder().setColor(Color.GREEN).setDescription("Du wurdest vom Server `" + event.getGuild().getName() + "` gebannt. Grund: `" + event.getMessage().getContentDisplay().replace(STATIC.PREFIX, "").replace("ban", "").replace(args0, "") + "`").build()
                ).queue();});
            event.getGuild().getController().ban(event.getMessage().getMentionedMembers().get(0), 0).queue();
            success.setColor(Color.GREEN);
            success.setDescription("Erfolgreich "+ event.getGuild().getMemberById(member).getAsMention()+" gebannt");
            event.getTextChannel().sendMessage(success.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        }else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `ban` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
