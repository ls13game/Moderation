package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cmdkick implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        String member = event.getMessage().getMentionedMembers().get(0).getUser().getId();
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        EmbedBuilder kick = new EmbedBuilder();
        if(event.getMember().hasPermission(Permission.KICK_MEMBERS)){
            if (args.length <1){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte Erwähne einen User");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if (event.getMessage().getMentionedMembers().size() <0 ){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte Erwähne einen User");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if(!event.getGuild().getSelfMember().canInteract(event.getMessage().getMentionedMembers().get(0))){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Ich habe keine Rechte dazu");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if (event.getGuild().getOwner().getUser().getId().equals(member)) {
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Du kannst nicht den Serverowner kicken");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {
                    msg.delete().queueAfter(20, TimeUnit.SECONDS);
                });
                return;
            }
            if (event.getAuthor().getId() == member){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Möchtest du dich wirklich selbst kicken?");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {
                    msg.delete().queueAfter(20,TimeUnit.SECONDS);
                });
            }
            String args0 = args[0];
            event.getGuild().getMemberById(member).getUser().openPrivateChannel().queue((channel) -> {
                kick.setColor(Color.GREEN);
                kick.setTitle("Du wurdest gekickt");
                kick.setDescription("Du wurdest vom Server `" + event.getGuild().getName() + "` Grund: `" + event.getMessage().getContentDisplay().replace(STATIC.PREFIX, "").replace("kick", "").replace(args0, "") + "` Um zu joinen klicke [Hier](" + event.getTextChannel().createInvite().setMaxUses(1).complete().getURL() + ")");
                event.getJDA().getUserById(member).openPrivateChannel().complete().sendMessage(kick.build()).queue();
                event.getGuild().getController().kick(member).queue();
                success.setColor(Color.green);
                success.setTitle("Erfolgreich");
                success.setDescription("Member " + event.getGuild().getMemberById(member).getAsMention() + " wurde erfolgreich gekickt");
                event.getTextChannel().sendMessage(success.build()).queue(msg -> {
                    msg.delete().queueAfter(20, TimeUnit.SECONDS);
                });
            });
                } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berrechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            return;
        }
        }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `kick` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
