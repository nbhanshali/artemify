//package com;
//
//import com.gateway.FileType;
//import com.gateway.GatewayCreator;
//import com.gateway.IGateway;
//import com.presenters.LanguagePresenter;
//import com.presenters.LanguageType;
//import com.presenters.PresenterCreator;
//import com.useCase.*;
//import java.io.IOException;
//
//
///**
// * A class with a main method where we would start the program.
// */
//public class ProgramStart {
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        // Initialize a SerGateway to read all entities from .ser files to the program
//        String filePath = System.getProperty("user.dir") + "//phase1//src//";
//        GatewayCreator gatewayCreator = new GatewayCreator();
//        IGateway ioGateway = gatewayCreator.createIGateway(FileType.SER, filePath);
//        UserEntityContainer users = ioGateway.readUsersFromFile();
//        PlaylistEntityContainer playlists = ioGateway.readPlaylistsFromFile();
//        SongEntityContainer songs = ioGateway.readSongsFromFile();
//
//        // Initialize Language Presenter
//        PresenterCreator presenterCreator = new PresenterCreator();
//        LanguagePresenter languagePresenter = presenterCreator.createLanguagePresenter(LanguageType.ENGLISH);
//
//        // Initialize the UserAccess, PlaylistManager, SongManager and Queue for only once
//        UserAccess acctServiceManager = new UserAccess(users);
//        PlaylistManager playlistManager = new PlaylistManager(playlists);
//        SongManager songManager = new SongManager(acctServiceManager, playlistManager, songs);
//        Queue queue = new Queue();
//
//        // initialize the one and only PageCreator object that will be used by all controllers in the system
//        PageCreator pageCreator = new PageCreator(languagePresenter, acctServiceManager, queue,
//                playlistManager, songManager, "");
//
//        // Main program starts
//        PageController systemMainPage = pageCreator.creat(PageType.MAIN_PAGE);
//        systemMainPage.invokes();
//
//        // Write the updated entities to .ser files
//        ioGateway.saveToFile(users, "Users.ser");
//        ioGateway.saveToFile(playlists, "Playlists.ser");
//        ioGateway.saveToFile(songs, "Songs.ser");
//    }
//}
