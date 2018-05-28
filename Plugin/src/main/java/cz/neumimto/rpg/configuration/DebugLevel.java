package cz.neumimto.rpg.configuration;

public enum DebugLevel {
    NONE(0, false, false),
    BALANCE(1, true, false),
    DEVELOP(2, true, true);

    private int i;
    private final boolean balance;
    private final boolean develop;

    DebugLevel(int i, boolean balance, boolean develop) {
        this.i = i;
        this.balance = balance;
        this.develop = develop;
    }

    public int getLevel() {
        return i;
    }

    public boolean isBalance() {
        return balance;
    }

    public boolean isDevelop() {
        return develop;
    }
}