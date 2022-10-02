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
    val songs = JSONSongStorage()

    fun startMenu() {
        var choice: Int
        do {
            choice = songView.mainMenu()
            when (choice) {
                1 -> add()
                2 -> listAll()
                3 -> updateSong()
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

    fun updateSong(){
        songView.listSongs(songs)
        println()
        val songToUpdate = findSong(songView.findSong())
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
            songs.updateSong(updatedSong,songToUpdate)
        }

    }

}






