package songmanager.console.controllers

import songmanager.console.views.songView

class SongController {
    var songView = songView()

    fun startMenu() {
        var choice: Int
        do {
            choice = songView.mainMenu()
            when (choice) {
                1 -> add()
                0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (choice != 0)
    }

    fun add (){
        println("Hello world")
    }

}





