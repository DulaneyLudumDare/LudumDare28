package com.gregswebserver.ld28.util;

public class Debug {

    int debug = 5;
    int stack = 5;

    public void printDebug(int level, String message) {
        if (level <= debug) {
            message = getCaller(stack) + message;
            switch (level) {
                case 0: // My cat is on fire.
                    // These messages will always print, regardless of what happens.
                    // This level for production silence.
                case 1: // Very critical errors.
                    System.out.println("[SEVERE] " + message);
                    break;
                case 2: // More critical errors.
                    System.out.println("[WARNING] " + message);
                    break;
                case 3: // Normal messages
                    //This is a good level for most operation
                case 4: // Spam messages
                case 5: // Most spam messages
                    // I hope you like spam!
                    System.out.println("[INFO] " + message);
                    break;
                default: //Error so bad it broke the error system.
                    System.out.println("[ERROR] " + message);
            }
        } else if (level < 3) {
            System.out.println("[WARNING] An error occurred. Change debug level to view.");
        }
    }

    public void printStack(int level, Throwable e) {
        printDebug(level, e.getMessage());
        if (level <= debug) e.printStackTrace();

    }

    public String getCaller(int depth) {
        String message = "";
        if (debug < 3) return "";
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement ste : stackTrace) {
            if (!ste.getFileName().equals(stackTrace[0].getFileName())) {
                if (depth == 0) return message;
                message = ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ") " + message;
                depth--;
            }
        }
        return "";
    }
}
