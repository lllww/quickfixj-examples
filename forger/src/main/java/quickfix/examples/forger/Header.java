package quickfix.examples.forger;

public class Header {
    private String senderCompID;
    private String targetCompID;

    public Header() {
    }

    public String getSenderCompID() {
        return senderCompID;
    }

    public void setSenderCompID(final String senderCompID) {
        this.senderCompID = senderCompID;
    }

    public String getTargetCompID() {
        return targetCompID;
    }

    public void setTargetCompID(final String targetCompID) {
        this.targetCompID = targetCompID;
    }
}