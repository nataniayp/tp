package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.Rating;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CATEGORY_CODE, PREFIX_RATING);

        if (((!arePrefixesPresent(argMultimap, PREFIX_CATEGORY_CODE))
            && (!arePrefixesPresent(argMultimap, PREFIX_RATING)))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Rating rating = new Rating("0");
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        }
        Set<CategoryCode> categoryCodes = ParserUtil.parseCategories(argMultimap.getAllValues(PREFIX_CATEGORY_CODE));

        return new FilterCommand(categoryCodes, rating);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}