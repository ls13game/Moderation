package listeners;

import UTIL.SECRETS;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.io.IOException;

public class discordpw extends ListenerAdapter {

    public void onGuildJoin(GuildJoinEvent event) {
        JSONObject data = new JSONObject();
        data.put("server_count", event.getJDA().getGuilds().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());
        String discordpw_url = "https://bots.discord.pw/api/bots/415154495039864834/stats";



        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordtoken)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute();
            System.out.println("Successfully posted count for discord.pw!");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void onGuildLeave(GuildLeaveEvent event) {
        JSONObject data = new JSONObject();
        data.put("server_count", event.getJDA().getGuilds().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());
        String discordpw_url = "https://bots.discord.pw/api/bots/415154495039864834/stats";



        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordtoken)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute();
            System.out.println("Successfully posted count for discord.pw!");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
