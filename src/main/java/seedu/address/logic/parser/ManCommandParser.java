package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ManCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a ManCommand.
 */
public class ManCommandParser implements Parser<ManCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ManCommand
     * and returns a ManCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ManCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            seedu.address.logic.commands.ManCommand.MESSAGE_USAGE));
        }

        return new ManCommand(trimmedArgs);
    }
}
