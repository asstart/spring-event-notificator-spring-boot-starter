package home.learning.eventnotificator.formatter;

public enum MessageMacros {

    DATE("[date]"),
    EVENT("[event]");

    private String macrosName;

    private MessageMacros(String macros){
        macrosName = macros;
    }

    public String getMacrosName(){
        return macrosName;
    }
}
