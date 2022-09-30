package songmanager.console.controllers

import songmanager.console.models.JSONSongStorage
import songmanager.console.models.SongModel
import songmanager.console.views.songView

class SongController {
    var songView = songView()
    val songs = JSONSongStorage()

    fun startMenu() {
        var choice: Int
        do {
            choice = songView.mainMenu()
            when (choice) {
                1 -> add()
                2 -> listAll()
                5 -> findSong(songView.findSong())
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (choice != 0)
    }

    fun add(){
        var song = songView.addSong()
        songs.create(song)
    }

    fun listAll(){
        songView.listSongs(songs)
    }
    fun findSong(songName: String): SongModel? {
        val song = songs.findSongInJSON(songName)
        songView.displaySong(song)
        return song
    }

}






