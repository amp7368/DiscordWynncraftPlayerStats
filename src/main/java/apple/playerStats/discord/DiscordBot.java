package apple.playerStats.discord;

import apple.playerStats.discord.commands.CommandUpdate;
import apple.playerStats.discord.commands.DoCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class DiscordBot extends ListenerAdapter {
    private static final String PREFIX = "f!";
    private static final String UPDATE = PREFIX + "update";

    private static final HashMap<String, DoCommand> commandMap = new HashMap<>();
    public static String discordToken = "NjE4OTU3MDcyNjA3NzM5OTE3.Xsajwg.L9Y9MqQVCwds5OtHLmcvK9dysWs"; // my bot
    public static JDA client;

    public void enableDiscord() throws LoginException {
        JDABuilder builder = new JDABuilder(discordToken);
        builder.addEventListeners(this);
        client = builder.build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        commandMap.put(UPDATE, new CommandUpdate());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }
        // the author is not a bot

        String messageContent = event.getMessage().getContentStripped();
        // deal with the differenct commands
        for (String command : commandMap.keySet()) {
            if (messageContent.startsWith(command)) {
                commandMap.get(command).dealWithCommand(event);
                break;
            }
        }
    }
}
