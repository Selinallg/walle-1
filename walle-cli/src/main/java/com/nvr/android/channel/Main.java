package com.nvr.android.channel;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.nvr.android.channel.commands.Batch2Command;
import com.nvr.android.channel.commands.IWalleCommand;
import com.nvr.android.channel.commands.RemoveCommand;
import com.nvr.android.channel.commands.ShowCommand;
import com.nvr.android.channel.commands.PutCommand;
import com.nvr.android.channel.commands.BatchCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chentong on 20/11/2016.
 */

public final class Main {
    private Main() {
        super();
    }

    public static void main(final String[] args) throws Exception {
        final Map<String, IWalleCommand> subCommandList = new HashMap<String, IWalleCommand>();
        subCommandList.put("show", new ShowCommand());
        subCommandList.put("rm", new RemoveCommand());
        subCommandList.put("put", new PutCommand());
        subCommandList.put("batch", new BatchCommand());
        subCommandList.put("batch2", new Batch2Command());

        final WalleCommandLine walleCommandLine = new WalleCommandLine();
        final JCommander commander = new JCommander(walleCommandLine);

        for (Map.Entry<String, IWalleCommand> commandEntry : subCommandList.entrySet()) {
            commander.addCommand(commandEntry.getKey(), commandEntry.getValue());
        }
        try {
            commander.parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            commander.usage();
            System.exit(1);
            return;
        }

        walleCommandLine.parse(commander);

        final String parseCommand = commander.getParsedCommand();
        if (parseCommand != null) {
            subCommandList.get(parseCommand).parse();
        }
    }
}
