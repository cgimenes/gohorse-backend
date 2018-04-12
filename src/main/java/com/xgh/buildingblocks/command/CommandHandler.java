package com.xgh.buildingblocks.command;

public interface CommandHandler<T extends Command> {
    void execute(T command);
}
