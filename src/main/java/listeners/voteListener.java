package listeners;

import UTIL.SECRETS;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import org.discordbots.api.client.DiscordBotListAPI;
import org.discordbots.api.client.entity.SimpleUser;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static core.Main.jda;

/**
 * Created by Oskar
 * on 10.04.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class voteListener {

    private String botid = "417054220269649934";


    public voteListener(DiscordBotListAPI api){

        startChecking(api);

    }


    private void Check(Guild g, DiscordBotListAPI api){


        Role r = g.getRoleById("433296374176415766");
        List<String> votet = api.getVoterIds(SECRETS.DTOKEN);
        List<SimpleUser> voters = Collections.singletonList(api.getVoters(botid));
        for (int i=1; i<voters.size(); i++) {
            System.out.println("2");
            for (Member m : g.getMembers()) {

                    if (voters.contains(m.getUser().getId() == voters.get(i).getId())) {

                        System.out.println("1");

                        if (!m.getRoles().contains(r)) {
                            System.out.println("Triggered");
                            g.getController().addSingleRoleToMember(m, r).queue();

                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setAuthor("⬆ New Vote", null, m.getUser().getAvatarUrl());
                            eb.setImage(jda.getSelfUser().getAvatarUrl());
                            eb.setDescription(m.getAsMention() + " hat für uns auf gevotet! \n Willst du auch voten? [Klick hier](https://discordbots.org/bot/415154495039864834)");
                            eb.setFooter(getTime(), null);

                            g.getTextChannelById("417085838283767826").sendMessage(eb.build()).queue();

                        }
                    }
                }
            }
        }




    private void startChecking(DiscordBotListAPI api){

        Timer t = new Timer();
        t.schedule(new TimerTask() {

            public void run() {

                Check(jda.getGuildById("417054063948201984"), api);

            }
        }, Date.from(Instant.now()), 30000);
    }

    private static String getTime() {
        Date date = new Date();
        SimpleDateFormat eins = new SimpleDateFormat("EEEE 'um' k:m");
        return eins.format(date.getTime());
    }
}




