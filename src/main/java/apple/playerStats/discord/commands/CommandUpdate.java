package apple.playerStats.discord.commands;

import apple.playerStats.sheets.WriteStats;
import apple.playerStats.wynncraft.GetPlayerStats;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CommandUpdate implements DoCommand {
    @Override
    public void dealWithCommand(MessageReceivedEvent event) {
        List<Member> members = event.getGuild().getMembers();
        List<String> nicknames = new LinkedList<>();
        for (Member member : members) {
            String nickname = member.getNickname();
            if (nickname == null)
                nickname = member.getEffectiveName();
            nicknames.add(nickname);
        }
        try {
            WriteStats.writePlayer("253646208084475904", "appleptr16", GetPlayerStats.get("appleptr16"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.getChannel().sendMessage("recieved the command").queue();
    }
}
