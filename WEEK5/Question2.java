
class MovieTicket {
    private int seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}

class PremiumMovieTicket extends MovieTicket {
}

public class Question2 {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {
        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            return "ALLOWED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
                && fieldModifier.equals("protected")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess(
            "protected",
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(classifyAccess(
            "protected",
            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
