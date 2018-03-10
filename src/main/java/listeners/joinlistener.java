package listeners;

import UTIL.SECRETS;
import UTIL.STATIC;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class joinlistener extends ListenerAdapter {
    EmbedBuilder anleitung = new EmbedBuilder();
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        anleitung.setColor(Color.GREEN);
        anleitung.setTitle("Anleitung");
        anleitung.setDescription("Hallo ich bin der Moderationsbot\n" +
                "Am besten Setzt du die Channel #reports und #support nur für dein Team sichtbar\n" +
                "Was mach ich?" );
        anleitung.addField("Automatischer Link report","Ich schreibe automatisch in den report channel wenn ein link gepostet wurde (ausgenommen er hat ein .png, .jpg oder .gif)",false);
        anleitung.addField("User report","Mit "+ STATIC.PREFIX+"report @User können deine User andere User reporten",false);
        anleitung.addField("Support anfrage","Mit "+STATIC.PREFIX+"support Können deine User im jeweiligen channel Support anfragen im jeweiligen channel anfragen",false);
        event.getGuild().getOwner().getUser().openPrivateChannel().complete().sendMessage(anleitung.build()).queue();
        Guild guild = event.getGuild();
        GuildController controller = guild.getController();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                controller.createCategory("Moderation").queue(cat -> {

                    controller.modifyCategoryPositions()
                            .selectPosition(cat.getPosition())
                            .moveTo(0).queue();

                    String[] list = {"Reports","Support"};

                    Arrays.stream(list).forEach(s ->
                            controller.createTextChannel(s).queue(chan -> chan.getManager().setParent((Category) cat).queue())
                    );
                });

            }
        }, 500);

        String token = SECRETS.DTOKEN;
        String botId = "415154495039864834";

        int serverCount = event.getJDA().getGuilds().size();

        JSONObject obj = new JSONObject()
                .put("server_count", serverCount);

        try {
            Unirest.post("https://discordbots.org/api/bots/" + botId + "/stats")
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .body(obj.toString())
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}
