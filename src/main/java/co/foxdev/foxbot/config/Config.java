/*
 * This file is part of Foxbot.
 *
 *     Foxbot is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foxbot is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foxbot. If not, see <http://www.gnu.org/licenses/>.
 */

package co.foxdev.foxbot.config;

import co.foxdev.foxbot.FoxBot;
import co.foxdev.foxbot.config.yamlconfig.file.FileConfiguration;
import co.foxdev.foxbot.config.yamlconfig.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class Config
{
    private final FoxBot foxbot;

    private File configFile = new File("config.yml");
    private File permsFile = new File("permissions.yml");

    private FileConfiguration botConfig;
    private FileConfiguration botPermissions;

    // ---------
    // Bot owner
    // ---------

    private String botOwner;

    // -----------
    // Bot section
    // -----------

    private String botNick;
    private String botIdent;
    private String botRealName;

    // --------------
    // Server section
    // --------------

    private String serverAddress;
    private int serverPort;
    private boolean serverSsl;
    private boolean acceptInvalidSsl;
    private String serverPassword;
    private List<String> serverChannels;

    // ------------
    // Auth section
    // ------------

    private boolean useNickserv;
    private String nickservPassword;
    private boolean usersMustBeVerified;
    private boolean matchUsersByHostmask;

    // -----------------------
    // User-punishment section
    // -----------------------

    private int unbanTimer;

    // ------------
    // Misc section
    // ------------

    private char commandPrefix;
    private boolean autoJoinOnInvite;
    private boolean autoRejoinOnKick;
    private long autoRejoinDelay;
    private long kickDelay;
    private boolean autoNickChange;
    private boolean autoReconnect;
    private long messageDelay;
    private boolean mungeUsernames;
    private List<String> ignoredChannels;
    private List<String> greetingChannels;
    private String greetingMessage;
    private boolean greetingNotice;

    // --------------
    // Sounds Section
    // --------------

    private String soundURL;
    private String soundExtension;

    // ----
    // Help
    // ----

    private List<String> helpLines;

    public Config(FoxBot foxbot)
    {
        this.foxbot = foxbot;
        botConfig = new YamlConfiguration();
        botPermissions = new YamlConfiguration();
        loadConfig();
    }

    private void loadConfig()
    {
        botConfig.saveResource("config.yml", false);
        botConfig.saveResource("permissions.yml", false);

        try
        {
            botConfig.load(configFile);
            botPermissions.load(permsFile);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        // ---------
        // Bot owner
        // ---------

        botOwner = botConfig.getString("bot-owner");

        // -----------
        // Bot section
        // -----------

        botNick = botConfig.getString("bot.nick");
        botIdent = botConfig.getString("bot.ident");
        botRealName = botConfig.getString("bot.realname");

        // --------------
        // Server section
        // --------------

        serverAddress = botConfig.getString("server.address");
        serverPort = botConfig.getInt("server.port");
        serverSsl = botConfig.getBoolean("server.ssl");
        acceptInvalidSsl = botConfig.getBoolean("server.accept-invalid-ssl-cert");
        serverPassword = botConfig.getString("server.password");
        serverChannels = botConfig.getStringList("server.channels");

        // ------------
        // Auth section
        // ------------

        useNickserv = botConfig.getBoolean("auth.use-nickserv");
        nickservPassword = botConfig.getString("auth.nickserv-password");
        usersMustBeVerified = botConfig.getBoolean("auth.users-must-be-verified");
        matchUsersByHostmask = botConfig.getBoolean("auth.match-users-by-hostmask");

        // -----------------------
        // User-punishment section
        // -----------------------

        unbanTimer = botConfig.getInt("user-punishment.unban-timer");

        // ------------
        // Misc section
        // ------------

        commandPrefix = botConfig.getString("misc.command-prefix").toCharArray()[0];
        autoJoinOnInvite = botConfig.getBoolean("misc.auto-join-on-invite");
        autoRejoinOnKick = botConfig.getBoolean("misc.auto-rejoin-on-kick");
        autoRejoinDelay = botConfig.getLong("misc.auto-rejoin-delay");
        kickDelay = botConfig.getLong("misc.kick-delay");
        autoNickChange = botConfig.getBoolean("misc.auto-nick-change");
        autoReconnect = botConfig.getBoolean("misc.auto-reconnect");
        messageDelay = botConfig.getLong("misc.message-delay");
        mungeUsernames = botConfig.getBoolean("misc.munge-usernames");
        ignoredChannels = botConfig.getStringList("misc.ignored-channels");
        greetingChannels = botConfig.getStringList("misc.channels-to-greet");
        greetingMessage = botConfig.getString("misc.greeting-message");
        greetingNotice = botConfig.getBoolean("misc.send-greeting-as-notice");

        // --------------
        // Sounds Section
        // --------------

        soundURL = botConfig.getString("sounds.sound-url");
        soundExtension = botConfig.getString("sounds.sound-extension");

        // ----
        // Help
        // ----

        helpLines = botConfig.getStringList("help");

    }

    public void reload()
    {
        loadConfig();
        foxbot.getZncConfig().reload();
    }

    // ---------
    // Bot owner
    // ---------

    public String getBotOwner()
    {
        return botOwner;
    }

    // -----------
    // Bot section
    // -----------

    public String getBotNick()
    {
        return botNick;
    }

    public String getBotIdent()
    {
        return botIdent;
    }

    public String getBotRealName()
    {
        return botRealName;
    }

    // --------------
    // Server section
    // --------------

    public String getServerAddress()
    {
        return serverAddress;
    }

    public int getServerPort()
    {
        return serverPort;
    }

    public boolean getServerSsl()
    {
        return serverSsl;
    }

    public boolean getAcceptInvalidSsl()
    {
        return acceptInvalidSsl;
    }

    public String getServerPassword()
    {
        return serverPassword;
    }

    public List<String> getChannels()
    {
        return serverChannels;
    }

    // ------------
    // Auth section
    // ------------

    public boolean useNickserv()
    {
        return useNickserv;
    }

    public String getNickservPassword()
    {
        return nickservPassword;
    }

    public boolean getUsersMustBeVerified()
    {
        return usersMustBeVerified;
    }

    public boolean getMatchUsersByHostmask()
    {
        return matchUsersByHostmask;
    }

    // -----------------------
    // User-punishment section
    // -----------------------

    public int getUnbanTimer()
    {
        return unbanTimer;
    }

    // ------------
    // Misc section
    // ------------

    public char getCommandPrefix()
    {
        return commandPrefix;
    }

    public boolean getAutoJoinOnInvite()
    {
        return autoJoinOnInvite;
    }

    public boolean getAutoRejoinOnKick()
    {
        return autoRejoinOnKick;
    }

    public long getAutoRejoinDelay()
    {
        return autoRejoinDelay;
    }

    public long getKickDelay()
    {
        return kickDelay;
    }

    public boolean getAutoNickChange()
    {
        return autoNickChange;
    }

    public boolean getAutoReconnect()
    {
        return autoReconnect;
    }

    public Long getMessageDelay()
    {
        return messageDelay;
    }

    public boolean getMungeUsernames()
    {
        return mungeUsernames;
    }

    public List<String> getIgnoredChannels()
    {
        return ignoredChannels;
    }

    public List<String> getGreetingChannels()
    {
        return greetingChannels;
    }

    public String getGreetingMessage()
    {
        return greetingMessage;
    }

    public boolean getGreetingNotice()
    {
        return greetingNotice;
    }

    // --------------
    // Sounds Section
    // --------------

    public String getSoundURL()
    {
        return soundURL;
    }

    public String getSoundExtension()
    {
        return soundExtension;
    }

    // ----
    // Help
    // ----

    public List<String> getHelpLines()
    {
        return helpLines;
    }

    // ------------
    // File objects
    // ------------

    public FileConfiguration getBotPermissions()
    {
        return botPermissions;
    }
}