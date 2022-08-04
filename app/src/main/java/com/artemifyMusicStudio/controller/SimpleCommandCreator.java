package com.artemifyMusicStudio.controller;

public interface SimpleCommandCreator {
    SimpleCommand create(CommandItemType type);
}
