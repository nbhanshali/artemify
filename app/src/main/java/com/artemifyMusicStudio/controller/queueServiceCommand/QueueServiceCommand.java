package com.artemifyMusicStudio.controller.queueServiceCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.useCase.Queue;

public abstract class QueueServiceCommand implements SimpleCommand {
    protected final Queue queueService;

    protected QueueServiceCommand(Queue queueService) {
        this.queueService = queueService;
    }
}
