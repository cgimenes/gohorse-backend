package com.xgh.buildingblocks.command;

import javax.transaction.Transactional;

public interface CommandHandler<T extends Command> {
    @Transactional
    void execute(T command);
}
