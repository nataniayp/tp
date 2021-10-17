package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;


/**
 * Represents the category of the Contact
 */
public class CategoryCode {
    public static final String MESSAGE_CONSTRAINTS =
            "Category codes should only be one of the following: att, fnb, com, acc, tpt, oth";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final List<String> CATEGORY_VALUES = Arrays.asList("att", "fnb", "com", "acc", "tpt", "oth");

    private enum Category { ATT, FNB, COM, ACC, TPT, OTH }


    //TODO(HK): Re-look at VALIDATION_REGEX
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private Category category;
    /**
     * Constructs a {@code CategoryCode}.
     *
     * @param categoryCode A valid categoryCode.
     */
    public CategoryCode(String categoryCode) {
        requireNonNull(categoryCode);
        checkArgument(isValidCategory(categoryCode), MESSAGE_CONSTRAINTS);
        category = stringToCategory(categoryCode);
    }

    /**
     * Returns true if a given string is a valid category code.
     */
    public static boolean isValidCategory(String code) {
        String category = code.toLowerCase();
        return CATEGORY_VALUES.contains(category) && code.matches(VALIDATION_REGEX);
    }

    /**
     * Converts the given category code into a Category object
     * @param code to be turned into enum object
     * @return Category object
     */
    private Category stringToCategory(String code) {
        int category = CATEGORY_VALUES.indexOf(code.toLowerCase());
        if (category == 0) {
            return Category.ATT;
        } else if (category == 1) {
            return Category.FNB;
        } else if (category == 2) {
            return Category.COM;
        } else if (category == 3) {
            return Category.ACC;
        } else if (category == 4) {
            return Category.TPT;
        } else if (category == 5) {
            return Category.OTH;
        } else {
            // Remove this portion in next iteration
            throw new RuntimeException("PROBLEM WITH CATEGORY CODES");
        }
    }

    @Override
    public String toString() {
        return category.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryCode // instanceof handles nulls
                && category.equals(((CategoryCode) other).category)); // state check
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
