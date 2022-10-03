package songmanager.console.views

import songmanager.console.helpers.*
import songmanager.console.models.SongModel

class songView {

    fun mainMenu(): Int {
        println("|                Main Menu               |")
        println("|----------------------------------------|")
        println("| 1. Add a Song                          |")
        println("| 2. List all Songs                      |")
        println("| 3. Update a Song                       |")
        println("| 4. Delete a Song                       |")
        println("|----------------------------------------|")
        println("| 5. Search for a Song                   |")
        println("| 6. Filter by Artist                    |")
        println("| 7. Calculate total length of Playlist  |")
        println("| 8. Sort by Year                        |")
        println("|----------------------------------------|")
        println("| 0. Exit                                |")
        println("|----------------------------------------|")
        println()
        println("| Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && input.isNotEmpty())
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
            println("Please enter the duration of the Song i.e Double(x.xx):")
            input = readLine()!!
        } while (!validateDouble(input) || !isTimeValid(input))
        newSong.duration = input.toDouble()


        do {
            println("Please enter the Year the Song was Released i.e Int(xxxx):")
            input = readLine()!!
        } while (!validateInt(input))
        newSong.releaseYear = input.toInt()

        do {
            println("Did the Song ever win an award? i.e Boolean(true,false):")
            input = readLine()!!
            input = handleMisInput(input)
        } while (!validateBool(input))
        newSong.wonAward = input.toBoolean()

        listSongs(mutableListOf(newSong))
        return newSong
    }

    fun listSongs(songs: List<SongModel>) {
        if (songs.isEmpty()) {
            println("There are currently no Songs on the system.....")
        } else {
            for (song in songs) {
                println("| Id | Title | Artist |  Duration  |  Release Year  |  Did it win an Award? |")
                println("| " + song.id + " | " + song.title + " | " + song.artist + " | " + song.duration.toString().split(".")[0]+"m "+ song.duration.toString().split(".")[1]+"s"+ " | " + song.releaseYear + " | " + song.wonAward + " | ")
                println()
            }
        }
        println()
        println("Press Enter to Continue....")
        readln()
    }

    fun findSong(useCase: String): String {
        var songName: String
        do {
            println(useCase)
            songName = readLine()!!
        } while (!validateString(songName))

        return songName
    }

    fun updateSong(): Int {
        println()
        println("| What Value would you like to update? |")
        println("|--------------------------------------|")
        println("| 1. Title                             |")
        println("| 2. Artist                            |")
        println("| 3. Duration                          |")
        println("| 4. Release Year                      |")
        println("| 5. Award Won                         |")
        println("|--------------------------------------|")
        println("| 0. Return to Main Menu               |")
        println("|--------------------------------------|")
        println()
        println("| Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -1
    }

    fun deleteSong() {
        println("Song Successfully Deleted....")

        println()
        println("Press Enter to Continue....")
        readln()
    }

    fun filterByArtistsName(): String {
        println("Please enter the name of the artist you would like to filter by")
        return readLine()!!
    }

}

//https://discuss.kotlinlang.org/t/printing-in-colors/22492