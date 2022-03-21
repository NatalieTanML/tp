package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a MeetingEntry's date and time in the meeting entry list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class MeetingDateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be formatted as d MMM uuuu h:mma; e.g. 21 Apr 2021 2:30pm";
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("d MMM uuuu h:mma")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDateTime datetime;

    /**
     * Constructs a {@code MeetingDateTime}.
     *
     * @param datetime A {@code String} representing the meeting date and time, formatted according
     * to {@link #DATETIME_FORMAT}.
     */
    public MeetingDateTime(String datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = parseDateTime(datetime);
    }

    /**
     * Constructs a {@code MeetingDateTime}.
     *
     * @param datetime A {@code LocalDateTime} representing the meeting date and time.
     */
    public MeetingDateTime(LocalDateTime datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = datetime;
    }

    /**
     * Returns true if a given {@code String} is a valid date and time.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} represents a valid date and time.
     */
    public static boolean isValidDateTime(String test) {
        try {
            parseDateTime(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if a given {@code LocalDateTime} is a valid date and time.
     *
     * @param test The {@code LocalDateTime} to test.
     * @return True, if the {@code LocalDateTime} is a valid date and time in the Gregorian calendar.
     */
    public static boolean isValidDateTime(LocalDateTime test) {
        try {
            parseDateTime(test.format(DATETIME_FORMAT));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts the given string to its date and time representation.
     *
     * @param dateTime The string to convert, formatted according to {@link #DATETIME_FORMAT}.
     * @return A datetime representation of {@code dateTime}.
     * @throws DateTimeParseException If {@code dateTime} is not formatted according to {@link #DATETIME_FORMAT}.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            // TODO: update this once we've figured out exception handling.
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String toString() {
        return datetime.format(DATETIME_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDateTime // instanceof handles nulls
                && datetime.equals(((MeetingDateTime) other).datetime)); // state check
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }

}
