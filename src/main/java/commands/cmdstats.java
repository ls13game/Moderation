package commands;

import UTIL.SECRETS;
import UTIL.STATIC;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class cmdstats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
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
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Statistic");
        info.setColor(Color.GREEN);
        info.addField("Totale Server",String.valueOf(event.getJDA().getGuilds().size()),false);
        info.addField("Total users",String.valueOf(event.getJDA().getUsers().stream()
                .filter(u -> !u.isBot()).collect(Collectors.toList()).size()), false);
        event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }

    @Override
    public String help() {
        return null;
    }
}
