package com.xdg.buildingblocks;

public abstract class CommandHandler<T extends Command> {
    public abstract void execute(T command);
}
