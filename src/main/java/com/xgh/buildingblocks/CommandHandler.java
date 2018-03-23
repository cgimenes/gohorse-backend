package com.xgh.buildingblocks;

public interface CommandHandler<T extends Command> {
    void execute(T command);
}
