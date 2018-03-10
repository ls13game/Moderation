package listeners;

import UTIL.SECRETS;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.JSONObject;

public class leavelistener extends ListenerAdapter{
    public void onGuildLeave(GuildLeaveEvent event) {
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
