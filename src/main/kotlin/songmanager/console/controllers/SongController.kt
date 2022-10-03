package songmanager.console.controllers

import songmanager.console.helpers.validateBool
import songmanager.console.helpers.validateDouble
import songmanager.console.helpers.validateInt
import songmanager.console.helpers.validateString
import songmanager.console.models.JSONSongStorage
import songmanager.console.models.SongModel
import songmanager.console.views.songView

class SongController {
    var songView = songView()
    var songs = JSONSongStorage()

    fun startMenu() {
        var choice: Int
        do {
            choice = songView.mainMenu()
            when (choice) {
                1 -> add()
                2 -> listAll()
                3 -> updateSong()
                4 -> deleteSong()
                5 -> findSong(songView.findSong("Please Enter the title of the song you wish to search for..."))
                6 -> filterByArtistsName()
                7 -> calculateTotalLengthOfPlaylist()
                8 -> sortbyYear()
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (choice != 0)
    }

    fun add(){
        val song = songView.addSong()
        songs.create(song)
    }

    fun listAll(){
        songView.listSongs(songs.songs)
    }
    fun findSong(songName: String): SongModel? {
        val song = songs.findSongInJSON(songName)
        if (song == null)
            println("There is no Song on the system by that name.....")

        return song
    }

    fun updateSong(){
        songView.listSongs(songs.songs)
        if(songs.songs.size != 0) {
            val songToUpdate = findSong(songView.findSong("Please Enter the title of the song you wish to Update..."))
            val updatedSong = SongModel()
            var input = ""

            var choice: Int
            do {
                choice = songView.updateSong()
                when (choice) {
                    1 -> {
                        do {
                            println("Please enter the updated title of the Song:")
                            updatedSong.title = readLine()!!
                        } while (!validateString(updatedSong.title))
                    }
                    2 -> {
                        do {
                            println("Please enter the updated Artist of the Song:")
                            updatedSong.artist = readLine()!!
                        } while (!validateString(updatedSong.artist))
                    }
                    3 -> {
                        do {
                            println("Please enter the updated duration of the Song:")
                            input = readLine()!!
                        } while (!validateDouble(input))
                        updatedSong.duration = input.toDouble()
                    }
                    4 -> {
                        do {
                            println("Please enter the updated Year the Song was Released:")
                            input = readLine()!!
                        } while (!validateInt(input))
                        updatedSong.releaseYear = input.toInt()
                    }
                    5 -> {
                        do {
                            println("Has the Song won an award?")
                            input = readLine()!!
                        } while (!validateBool(input))
                        updatedSong.wonAward = input.toBoolean()
                    }
                    0 -> println("Returning to Menu")
                    else -> println("Invalid Option")
                }
                println()
            } while (choice != 0)

            if (songToUpdate != null) {
                songs.updateSong(updatedSong, songToUpdate)
            }
        }
    }

    fun deleteSong(){
        songView.listSongs(songs.songs)
        println()
        val songToDelete = findSong(songView.findSong("Please Enter the title of the song you wish to Delete..."))
        if (songToDelete != null) {
            songs.removeSong(songToDelete)
            songView.deleteSong()
        }
    }

    fun filterByArtistsName(){
        val artistName = songView.filterByArtistsName()
        songView.listSongs(songs.filterByArtistsName(artistName))
    }

    fun calculateTotalLengthOfPlaylist(){
        val allSongs = songs.listAll()
        var duration = 0.00
        for (song in allSongs){
            duration += song.duration
        }
        println(duration)
    }

    fun sortbyYear(){
        songView.listSongs(songs.sortBy())
    }

}






