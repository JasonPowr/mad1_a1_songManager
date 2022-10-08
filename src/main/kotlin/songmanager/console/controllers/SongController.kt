package songmanager.console.controllers

import songmanager.console.helpers.*
import songmanager.console.models.JSONSongStorage
import songmanager.console.models.SongModel
import songmanager.console.views.songView
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toDuration

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
                5 -> {
                    if (songs.songs.isEmpty()) {
                        println("There are currently no songs in the system...")

                        println()
                        println("Press Enter to Continue....")
                        readln()
                    }else{
                        findSongs(songView.findSong("Please Enter the title of the song you wish to search for..."))
                    }
                }
                6 -> filterByArtistsName()
                7 -> calculateTotalLengthOfPlaylist()
                8 -> sortbyYear()
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (choice != 0)
    }

    fun add() {
        val song = songView.addSong()
        songs.create(song)
    }

    fun listAll() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")
        }
        songView.listSongs(songs.songs)
    }

    fun findSong(songName: String): SongModel? {
        val song = songs.findSongInJSON(songName)
        if (song == null) {
            println("There is no Song on the system by that name.....")
        }
        return song
    }

    fun findSongs(songName: String) {
            val songs = songs.findSongsInJSON(songName)
            if (songs.isEmpty()) {
                println("No Songs found by that name")
            }
            songView.listSongs(songs)
    }

    fun updateSong() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")
        }
        songView.listSongs(songs.songs)
        if (songs.songs.size != 0) {
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
                        } while (!validateDouble(input) || !isTimeValid(input))
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
                            input = handleMisInput(input)
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

    fun deleteSong() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")

            println()
            println("Press Enter to Continue....")
            readln()

        } else {
            songView.listSongs(songs.songs)
            println()
            val songToDelete = findSong(songView.findSong("Please Enter the title of the song you wish to Delete..."))
            if (songToDelete != null) {
                songs.removeSong(songToDelete)
                songView.deleteSong()
            }
        }

    }

    fun filterByArtistsName() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")

            println()
            println("Press Enter to Continue....")
            readln()
        } else {
            val artistName = songView.filterByArtistsName(songs.songs)
            songView.listSongs(songs.filterByArtistsName(artistName))
        }
    }

    fun calculateTotalLengthOfPlaylist() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")

            println()
            println("Press Enter to Continue....")
            readln()
        } else {
            val allSongs = songs.listAll()
            var duration = 0.00
            for (song in allSongs) {
                duration += song.duration
            }
            println(duration.minutes)
        }
    }

    fun sortbyYear() {
        if (songs.songs.isEmpty()) {
            println("There are currently no songs in the system...")
        }
        songView.listSongs(songs.sortBy())
    }

}







