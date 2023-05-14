package me.erick;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.GuildMessageReceivedEvent;
import org.example.MyBotoverride;

class MyBot extends ListenerAdapter implements MyBotoverride {

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault("MTA3NDA3Nzg1MjMyMTk4NDcyMw.GmOifT.mjwYzdxBstHoIYydHE8G1wub_I9WvuawpljrbE");
        builder.addEventListeners(new MyBot());
        builder.build();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        if (message[0].equalsIgnoreCase(".nuke")) {
            event.getChannel().getIterableHistory().forEach(msg -> msg.delete().queue());
            event.getChannel().sendMessage("Nuked!").queue();
        }
    }
}

public class PingCommand extends ListenerAdapter {
    private final JDA jda;

    public PingCommand(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(".ping")) {
            long startTime = System.currentTimeMillis();

            event.getChannel().sendMessage("Pong!").queue(response -> {
                long endTime = System.currentTimeMillis();
                long ping = endTime - startTime;
                response.editMessageFormat("Pong! Latency is %d ms", ping).queue();
            });
        }
    }

    public JDA getJda() {
        return jda;
    }
}
