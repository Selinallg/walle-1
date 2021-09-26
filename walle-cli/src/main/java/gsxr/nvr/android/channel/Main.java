package gsxr.nvr.android.channel;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import gsxr.nvr.android.channel.commands.Batch2Command;
import gsxr.nvr.android.channel.commands.IMalleCommand;
import gsxr.nvr.android.channel.commands.RemoveCommand;
import gsxr.nvr.android.channel.commands.ShowCommand;
import gsxr.nvr.android.channel.commands.PutCommand;
import gsxr.nvr.android.channel.commands.BatchCommand;

import java.util.HashMap;
import java.util.Map;

public final class Main {
    private Main() {
        super();
    }

    public static void main(final String[] args) throws Exception {
        final Map<String, IMalleCommand> subCommandList = new HashMap<String, IMalleCommand>();
        subCommandList.put("show", new ShowCommand());
        subCommandList.put("rm", new RemoveCommand());
        subCommandList.put("put", new PutCommand());
        subCommandList.put("batch", new BatchCommand());
        subCommandList.put("batch2", new Batch2Command());

        final MalleCommandLine malleCommandLine = new MalleCommandLine();
        final JCommander       commander        = new JCommander(malleCommandLine);

        for (Map.Entry<String, IMalleCommand> commandEntry : subCommandList.entrySet()) {
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

        malleCommandLine.parse(commander);

        final String parseCommand = commander.getParsedCommand();
        if (parseCommand != null) {
            subCommandList.get(parseCommand).parse();
        }
    }
}
