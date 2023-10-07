package Model;

public class CheckPostFormatModel {

    public static boolean isNonNegativeInt(int inputValue) {
        return inputValue >= 0;
    }

    public static boolean hasNoComma(String inputContent) {
        return !inputContent.contains(",");
    }

    public static boolean isValidDateTime(String dateTime) {
        // Define a regular expression pattern for a valid date-time format
        String dateTimePattern = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";

        if (!dateTime.matches(dateTimePattern)) {
            return false;
        }

        String[] parts = dateTime.split(" ");
        String[] dateParts = parts[0].split("/");
        String[] timeParts = parts[1].split(":");

        // Parse date and time components
        try {
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            int hour = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);

            // Validate date and time components
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || hour < 0 || hour > 23 || minutes < 0 || minutes > 59) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            // Parsing error occurred
            return false;
        }
    }
}

