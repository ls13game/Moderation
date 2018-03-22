package listeners;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static core.Main.jda;

/**
 * Created by Oskar
 * on 19.03.2018
 * github.com/oskardevkappa/
 */

public class gameHandler{


    private static Timer t;
    static int count = 0;


    static public Timer GameTimer() {
        System.out.println("Default Timer");
        t = new Timer();
        t.schedule(new TimerTask() {

            public void run() {
                System.out.println("run");
                jda.getPresence().setGame(Game.playing(""));

                if (count == 5) count = 1;
                switch (count) {
                    case 1:
                        jda.getPresence().setGame(Game.playing("with " + AllMembers(jda) + " Users!"));
                        break;
                    case 2:
                        jda.getPresence().setGame(Game.playing("on " + jda.getGuilds().size() + " Guilds!"));
                        break;
                    case 3:
                        jda.getPresence().setGame(Game.watching("out for Translators."));
                        break;
                    case 4:
                        jda.getPresence().setGame(Game.playing("mod!help"));
                        break;
                }
            }
        }, Date.from(Instant.now()), 10000);
        return t;
    }



    static public void stop(){
        System.out.println("Stop");
        t.cancel();
    }
    static public void start(){
        System.out.println("Start");
        GameTimer();

    }



    static private int AllMembers(JDA jda){
        int members = 0;

        for (Guild gu:jda.getGuilds() ) {
            members += gu.getMembers().size();
        }
        return members;
    }

    public static void CustomGame(String input){
        jda.getPresence().setGame(Game.playing(input));

    }


}

