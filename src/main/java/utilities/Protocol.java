package utilities;

public enum Protocol {

    HTTP(),
    HTTPS();

    Protocol() {
    }

    protected String get() {

        return name().toLowerCase() + "://";
    }
}
