package com.awesomeplaylist.api.controller

import com.awesomeplaylist.api.model.Song
import com.awesomeplaylist.api.service.SongsService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:5173", "http://localhost:3000"])
class SongsController(val songsService: SongsService) {

    @GetMapping("/songs")
    fun getAllSongs(@RequestParam sortParameter: String?, @RequestParam sortDirection: String?) = ResponseEntity.ok(songsService.getAllSongsSorted(sortParameter, sortDirection))

    @GetMapping("/songs/{songId}/details", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSongDetails(@PathVariable songId: Long) = ResponseEntity.ok(songsService.getSongDetails(songId))

    @PostMapping("/songs", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewSong(@RequestBody song: Song) = ResponseEntity.ok(songsService.addNewSong(song))

    @GetMapping("/playlists/{playlistId}/songs")
    fun getSongsForPlaylist(@PathVariable playlistId: Long, @RequestParam sortParameter: String?, @RequestParam sortDirection: String?): ResponseEntity<List<Song>> =
            ResponseEntity.ok(songsService.getPlaylistSongsSorted(playlistId, sortParameter, sortDirection))

}