package songmanager.console.views

import songmanager.console.models.SongModel

class songView {


    fun mainMenu(): Int {
        println("        Main Menu        ")
        println("-------------------------")
        println("     1. Add a Song")
        println("     2. List all Songs")
        println("     3. Update a Song")
        println("     4. Delete a Song")
        println("-------------------------")
        println("     0. Exit")
        println()
        println("Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
    }
    fun addSong(): SongModel{
        val newSong = SongModel()
        println()
        println("Please enter the name of the Song:")
        newSong.title = readLine()!!
        println("Please enter the name of the Artist:")
        newSong.artist = readLine()!!
        println("Please enter the duration of the Song:")
        newSong.duration = readln().toDouble()
        println("Please enter the Year the Song was Released:")
        newSong.releaseYear = readln().toInt()
        println("Did the Song ever win an award?")
        newSong.wonAward = readln().toBoolean()
        return newSong
    }
}

//https://discuss.kotlinlang.org/t/printing-in-colors/22492