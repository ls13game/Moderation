package commands;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cmduserinfo implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public void action(String[] args, MessageReceivedEvent event) {
        StringBuilder rawRoles = new StringBuilder();
        EmbedBuilder info = new EmbedBuilder().setColor(Color.GREEN);
        String author = event.getAuthor().getId();
        if (event.getMessage().getMentionedMembers().size() > 0) {
            User user = event.getMessage().getMentionedUsers().get(0);
            info.setTitle("ℹ"+user.getName());
            if (Dev.isDev(event.getMessage().getMentionedUsers().get(0))){
                info.setDescription("Ist einer meiner Devs");
            }
            //                           Just                                             Cpt.Slow                                    Passi
            if (user.getId().equals("327129699904126976")|| user.getId().equals("142670727236026368")|| user.getId().equals("170521798021087232")){
                info.addField("VIP","Comunity Staff",false);
            }
            info.addField("Guildjoin am:",event.getGuild().getMemberById(user.getId()).getJoinDate().format(DateTimeFormatter.ISO_LOCAL_DATE),false);
            info.addField("Onlinestatus:",event.getGuild().getMemberById(user.getId()).getOnlineStatus().getKey(),false);
            info.addField("Avatar URL:","[URL]("+event.getGuild().getMemberById(user.getId()).getUser().getAvatarUrl()+")",false);
            info.addField("Rollen:",""+event.getGuild().getMemberById(user.getId()).getRoles().size(),false);
            if (event.getGuild().getMemberById(user.getId()).getGame() == null){
                info.addField("Game:","nothing",false);
            }else {
                info.addField("Game",event.getGuild().getMemberById(user.getId()).getGame().getName(),false);
            }
            info.setThumbnail(user.getAvatarUrl());

            event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES);});
        }else {
            info.setTitle("ℹ"+ event.getAuthor().getName());
            if (Dev.isDev(event.getAuthor())) {
                info.setDescription("Ist einer meiner Devs");
            }
            if (author.equals("362270177712275491")|| author.equals("327129699904126976")||author.equals("142670727236026368")||author.equals("170521798021087232")){
                info.addField("VIP","Comunity Staff",false);
            }
            info.addField("Guildjoin am:", event.getGuild().getMemberById(author).getJoinDate().format(DateTimeFormatter.ISO_LOCAL_DATE), false);
            info.addField("Onlinestatus:", event.getGuild().getMemberById(author).getOnlineStatus().getKey(), false);
            info.addField("Avatar URL:","[URL]("+event.getGuild().getMemberById(author).getUser().getAvatarUrl()+")",false);
            info.addField("Rollen:", ""+event.getGuild().getMemberById(author).getRoles().size(), false);
            if (event.getGuild().getMemberById(author).getGame() == null) {
                info.addField("Game:", "Nothing", false);
            } else {
                info.addField("Game:", event.getGuild().getMemberById(author).getGame().getName(), false);
            }
            info.setThumbnail(event.getAuthor().getAvatarUrl());
            event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES);});
        }
    }

    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `userinfo` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }

    public String help() {
        return null;
    }
}
