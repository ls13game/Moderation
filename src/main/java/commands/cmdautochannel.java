package commands;

import com.sun.security.auth.callback.TextCallbackHandler;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import UTIL.STATIC;

import java.awt.Color;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zekro on 08.09.2017 / 20:25
 * DiscordBot.commands.guildAdministration
 * dev.zekro.de - github.zekro.de
 * © zekro 2017
 */

public class cmdautochannel implements Command, Serializable {

    // Hier werden die Autochannels mit der dazugehörigen Guild registriert
    private static HashMap<VoiceChannel, Guild> autochans = new HashMap<>();

    // Getter für Autochannel register
    public static HashMap<VoiceChannel, Guild> getAutochans() {
        return autochans;
    }

    // Getter für VoiceChannel by ID
    private static VoiceChannel getVchan(String id, Guild g) {
        return g.getVoiceChannelById(id);
    }

    // Getter für Guild by ID
    private static Guild getGuild(String id, JDA jda) {
        return jda.getGuildById(id);
    }

    // Sender für Error Messages
    private void error(TextChannel tc, String content) {
        tc.sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(content).build()).queue();
    }

    // Sender für normale Messages
    private void msg(TextChannel tc, String content) {
        tc.sendMessage(new EmbedBuilder().setColor(Color.green).setDescription(content).build()).queue();
    }

    /*
        Wenn der VC nicht "null" und noch nicht im Register ist,
        dann wird dieser dem hinzugefügt und in die Save File gespeichert.
    */
    private void setChan(String id, Guild g, TextChannel tc) {
        VoiceChannel vc = getVchan(id, g);

        if (vc == null) {
            error(tc, String.format("Voice channel with the ID `%s` does not exist.", id));
        } else if (autochans.containsKey(vc)) {
            error(tc, "This channel is just set as auto channel.");
        } else {
            msg(tc, String.format("Successfully set voice channel `%s` as auto channel.", vc.getName()));
        }
    }

    /*
        Wenn der VC nicht "null" ist und im Register eingetragen ist,
        dann wird dieser aus dem Register entfernt und in die Save File geschrieben.
    */
    private void unsetChan(String id, Guild g, TextChannel tc) {
        VoiceChannel vc = getVchan(id, g);

        if (vc == null) {
            error(tc, String.format("Voice channel with the ID `%s` does not exist.", id));
        } else if (!autochans.containsKey(vc)) {
            error(tc, String.format("Voice channel `%s` is not set as auto channel.", vc.getName()));
        } else {
            autochans.remove(vc);
            save();

            msg(tc, String.format("Successfully unset auto channel state of `%s`.", vc.getName()));
        }
    }



    /*
        Selbes wie oben für den Autochannel Hanlder, wenn der VC des
        Autochannels gelöscht wird.
    */
    public static void unsetChan(VoiceChannel vc) {
        autochans.remove(vc);
        save();
    }

    /*
        Nimmt alle VoiceChannels aus dem Register mit der Guild, von der
        aus der Command ausgeführt wurde und sendet sie als Embed Message.
    */
    private void listChans(Guild guild, TextChannel tc) {
        StringBuilder sb = new StringBuilder().append("**Auto Channel**\n\n");
        autochans.keySet().stream()
                .filter(c -> autochans.get(c).equals(guild))
                .forEach(c -> sb.append(String.format(":white_small_square:   `%s` *(%s)*\n", c.getName(), c.getId())));
        tc.sendMessage(new EmbedBuilder().setDescription(sb.toString()).build()).queue();
    }

    /*
        Speichert das Register mit IDs in einer Save File.
    */
    private static void save() {

        File path = new File("SERVER_SETTINGS/");
        if (!path.exists())
            path.mkdir();

        HashMap<String, String> out = new HashMap<>();

        System.out.println(autochans.size());
        autochans.forEach((v, g) -> out.put(v.getId(), g.getId()));

        try {
            FileOutputStream fos = new FileOutputStream("SERVER_SETTINGS/autochannels.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(out);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
        Läd das Register mit Guild-Getter und VC-Getter aus der Save File,
        wenn diese existiert.
    */
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
                    autochans.put(getVchan(vid, g), g);
                });

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        Guild g = event.getGuild();
        TextChannel tc = event.getTextChannel();

        // Help Message, wenn keine Channel ID angegeben ist.
        if (args.length < 1) {
            error(tc, help());
            return;
        }

        switch (args[0]) {

            case "list":
            case "show":
                listChans(g, tc);
                break;

            case "add":
            case "set":
                // Nimmt "set"/"add" aus den Arguments und übergibt nur die VC ID als Argument.
                if (args.length < 2)
                    tc.sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(help()).build()).queue();
                else
                    setChan(args[1], g, tc);
                break;

            case "remove":
            case "delete":
            case "unset":
                // Selbes wie in Z. 194
                if (args.length < 2)
                    tc.sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(help()).build()).queue();
                else
                    unsetChan(args[1], g, tc);
                break;

            default:
                // Wen ein nicht definiertes ARguemnt angegeben wurde wird eine Help MSG ausgegeben.
                error(tc, help());
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "**USAGE:**\n" +
                ":white_small_square:  `-autochannel set <Chan ID>`  -  Set voice chan as auto channel\n" +
                ":white_small_square:  `-autochannel unset <Chan ID>`  -  Unset voice chan as auto chan\n" +
                ":white_small_square:  `-autochannel list`  -  Display all registered auto chans\n";
    }

}