package songmanager.console.views

import songmanager.console.models.SongModel
import songmanager.console.helpers.*
import songmanager.console.models.JSONSongStorage

class songView {


    fun mainMenu(): Int {
        println("        Main Menu        ")
        println("-------------------------")
        println("     1. Add a Song")
        println("     2. List all Songs")
        println("     3. Update a Song")
        println("     4. Delete a Song")
        println("-------------------------")
        println("     5. Search for a Song")
        println("     0. Exit")
        println()
        println("Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -1
    }

    fun addSong(): SongModel {
        val newSong = SongModel()
        println()

        do {
            println("Please enter the title of the Song:")
            newSong.title = readLine()!!
        } while (!validateString(newSong.title))

        do {
            println("Please enter the name of the Artist:")
            newSong.artist = readLine()!!
        } while (!validateString(newSong.artist))

        var input = ""
        do {
            println("Please enter the duration of the Song:")
            input = readLine()!!
        } while (!validateDouble(input))
        newSong.duration = input.toDouble()


        do {
            println("Please enter the Year the Song was Released:")
            input = readLine()!!
        } while (!validateInt(input))
        newSong.releaseYear = input.toInt()

        do {
            println("Did the Song ever win an award?")
            input = readLine()!!
        } while (!validateBool(input))
        newSong.wonAward = input.toBoolean()


        return newSong
    }

    fun listSongs(songs: JSONSongStorage) {
        if (songs.songs.size == 0) {
            println("There are currently no Songs on the system.....")
        } else {
            println("Here is a list of all the current songs in the system:")
            println()
            for (song in songs.songs) {
                println("-------------------------")
                println("|Id: " + song.id)
                println("|Title: " + song.title)
                println("|Artist: " + song.artist)
                println("|Duration: " + song.duration)
                println("|Release Year: " + song.releaseYear)
                println("|Award Won: " + song.wonAward)
            }
        }
    }

    fun findSong(): String {
        var songName: String
        do {
            println("Please enter the name of the song")
            songName = readLine()!!
        }while (!validateString(songName))

        return songName
    }

    fun displaySong(song: SongModel?) {
        if (song == null) {
            println("No song Found by that name")
        } else {
            println()
            println("-------------------------")
            println("|Title: " + song.title)
            println("|Artist: " + song.artist)
            println("|Duration: " + song.duration)
            println("|Release Year: " + song.releaseYear)
            println("|Award Won: " + song.wonAward)
        }
    }

    fun updateSong(): Int{
        println()
        println("    Update Menu")
        println("-------------------------")
        println("     1. Title")
        println("     2. Artist")
        println("     3. Duration")
        println("     4. Release Year")
        println("     5. Award Won")
        println("-------------------------")
        println("     0. Return to Main Menu")
        println()
        println("Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -1
    }

}

//https://discuss.kotlinlang.org/t/printing-in-colors/22492