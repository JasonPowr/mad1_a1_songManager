package songmanager.console.views

import songmanager.console.models.SongModel
import songmanager.console.helpers.*

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
            -1
    }

    fun addSong(): SongModel {
        val newSong = SongModel()
        println()

        do {
            println("Please enter the name of the Song:")
            newSong.title = readLine()!!
        }while (!validateString(newSong.title))

        do {
            println("Please enter the name of the Artist:")
            newSong.artist = readLine()!!
        }while (!validateString(newSong.artist))

        var input = ""
        do {
            println("Please enter the duration of the Song:")
            input = readLine()!!
        }while (!validateDouble(input))
        newSong.duration = input.toDouble()


        do {
            println("Please enter the Year the Song was Released:")
            input = readLine()!!
        }while (!validateInt(input))
        newSong.releaseYear = input.toInt()

        do {
        println("Did the Song ever win an award?")
            input = readLine()!!
        }while (!validateBool(input))
        newSong.wonAward = input.toBoolean()

        return newSong
    }

    fun listSongs(){
        println()
        println("Here is a list of all the current songs in the system:")
    }
}

//https://discuss.kotlinlang.org/t/printing-in-colors/22492