package commands;

import anderes.Dev;
import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class cmdautochannel implements Command, Serializable {

    EmbedBuilder error = new EmbedBuilder();

    
    private static HashMap<VoiceChannel, Guild> autochannels = new HashMap<>();

    public static HashMap<VoiceChannel, Guild> getAutoChannels(){
        return autochannels;
    }

    public static VoiceChannel getVchan(String id, Guild g)  {
        return  g.getVoiceChannelById(id);
    }


    private static Guild getGuild(String id, JDA jda)  {
        return jda.getGuildById(id);
    }

    private void error(TextChannel tc, String content)  {
        tc.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
    }
    private void message(TextChannel tc, String content){
        tc.sendMessage(new EmbedBuilder().setColor(Color.GREEN).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
    }


    private void setChan(String id, Guild g, TextChannel tc){
        VoiceChannel vc = getVchan(id, g);

        if (vc == null)
            error(tc, "Bitte gib eine gültige Channel-ID ein!");
        else if (autochannels.containsKey(vc))
            error(tc, "Dieser Channel wurde bereits registriert.");
        else {
            autochannels.put(vc, g);
            save();
            message(tc, String.format("Der Channel `%s` wurde erfolgreich als AutoChannel gesetzt.", vc.getName()));
        }
    }

    private void unsetChan(String id, Guild g, TextChannel tc) {
        VoiceChannel vc = getVchan(id, g);

        if (vc == null)
            error(tc, "Bitte gib eine gültige Channel-ID ein!");
        else if (!autochannels.containsKey(vc))
            error(tc, "Dieser Channel ist kein AutoChannel!");
        else {
            autochannels.remove(vc);
            save();
            message(tc, String.format("Der AutoChannel `%s` wurde erfolgreich entfernt.", vc.getName()));
        }
    }

    public static void unsetChan(VoiceChannel vc) {
        autochannels.remove(vc);
        save();
    }

    private void listChans(Guild g, TextChannel tc) {
        StringBuilder sb = new StringBuilder().append("**AUTO-CHANNELS:\n\n**");
        autochannels.keySet().stream()
                .filter(vc -> autochannels.get(vc).equals(g))
                .forEach(vc -> sb.append(String.format(":white_small_square:   `%s` *(%s)*\n", vc.getName(), vc.getId())));
        tc.sendMessage(new EmbedBuilder().setDescription(sb.toString()).build()).queue();
    }

    private static void save(){
        File path = new File ("SERVER_SETTINGS/");
        if (!path.exists())
            path.mkdir();

        HashMap<String, String> out = new HashMap<>();

        autochannels.forEach((vc, g) -> out.put(vc.getId(), g.getId()));

        try {
            FileOutputStream fos = new FileOutputStream("SERVER_SETTINGS/autochannels.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(out);
            oos.close();
        }    catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void load(JDA jda) {
        File file = new File("SERVER_SETTINGS/autochannels.dat");
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashMap<String, String> out = (HashMap<String, String>) ois.readObject();
                ois.close();

                out.forEach((vid, gid) -> {
                    Guild g = getGuild(gid, jda);
                    autochannels.put(getVchan(vid, g), g);
                });

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }




    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder help = new EmbedBuilder().setColor(Color.GREEN).setTitle("Hilfe");
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)|| Dev.isDev(event.getAuthor())) {
            Guild g = event.getGuild();
            TextChannel tc = event.getTextChannel();

            if (args.length < 1) {
                help.addField("list","zeigt alle Autochannels",false);
                help.addField("add","Fügt einen Autochannel Über eine **ID** Hinzufügen",false);
                help.addField("unset","Löscht einen Autochannel",false);
                event.getTextChannel().sendMessage(help.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }

            switch (args[0]) {

                case "list":
                    listChans(g, tc);
                    break;

                case "add":
                    if (args.length < 2)
                        error(tc, help());
                    else
                        setChan(args[1], g, tc);
                    break;

                case "unset":
                    if (args.length < 2)
                        error(tc, help());
                    else
                        unsetChan(args[1], g, tc);
                    break;

                default:
                    error(tc, help());
            }

        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
    }

    public void executed(boolean success, MessageReceivedEvent event) {
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `Autochannel` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
