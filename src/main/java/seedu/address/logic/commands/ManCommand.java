package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the message usage of a command, and displays an error message
 * if the input is not a valid command.
 */
public class ManCommand extends Command {

    public static final String COMMAND_WORD = "man";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "help";
    private final String input;

    /**
     * Creates a {@code ManCommand} for the input command
     * @param input The input command
     */
    public ManCommand(String input) {
        this.input = input;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(getMessageUsage(input));
    }

    private String getMessageUsage(String input) {
        switch (input) {

        case AddCommand.COMMAND_WORD:
            return AddCommand.MESSAGE_USAGE;

        case EditCommand.COMMAND_WORD:
            return EditCommand.MESSAGE_USAGE;

        case DeleteCommand.COMMAND_WORD:
            return DeleteCommand.MESSAGE_USAGE;

        case ClearCommand.COMMAND_WORD:
            return ClearCommand.COMMAND_WORD;

        case FindCommand.COMMAND_WORD:
            return FindCommand.MESSAGE_USAGE;

        case ListCommand.COMMAND_WORD:
            return ListCommand.COMMAND_WORD;

        case ExitCommand.COMMAND_WORD:
            return ExitCommand.COMMAND_WORD;

        case HelpCommand.COMMAND_WORD:
            return HelpCommand.COMMAND_WORD;

        default:
            return "Invalid command";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ManCommand)) {
            return false;
        }

        ManCommand otherManCommand = (ManCommand) other;
        return input.equals(otherManCommand.input);
    }
}
