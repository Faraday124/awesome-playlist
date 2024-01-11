package com.awesomeplaylist.api.rest

import com.awesomeplaylist.api.rest.model.Playlist
import com.awesomeplaylist.api.rest.model.Song
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:5173", "http://localhost:3000"])
class SimpleController(val playlistService: PlaylistService, val songsService: SongsService) {


    @GetMapping("/hello")
    fun helloWorld() = ResponseEntity.ok("hello world")

    @GetMapping("/songs")
    fun getAllSongs(@RequestParam sortParameter: String?, @RequestParam sortDirection: String?) = ResponseEntity.ok(songsService.getAllSongsSorted(sortParameter, sortDirection))

    @GetMapping("/songs/{songId}/details")
    fun getSongDetails(@PathVariable songId: Long) = ResponseEntity.ok(songsService.getSongDetails(songId))

    @PostMapping("/songs")
    fun addNewSong(@RequestBody song: Song) = ResponseEntity.ok(songsService.addNewSong(song))

    @PostMapping("/playlists/{playlistId}/song/{songId}")//TODO
    fun addSongToPlaylist(@PathVariable playlistId: Long, @PathVariable songId: Long) = ResponseEntity.ok(playlistService.addSongToPlaylist(playlistId, songId))

    @DeleteMapping("/playlists/{playlistId}/song/{songId}")
    fun deleteSongFromPlaylist(@PathVariable playlistId: Long, @PathVariable songId: Long) = ResponseEntity.ok(playlistService.removeSongFromPlaylist(playlistId, songId))

    @GetMapping("/playlists/{playlistId}/songs")
    fun getSongsForPlaylist(@PathVariable playlistId: Long, @RequestParam sortParameter: String?, @RequestParam sortDirection: String?): ResponseEntity<List<Song>> =
            ResponseEntity.ok(songsService.getPlaylistSongsSorted(playlistId, sortParameter, sortDirection))

    @GetMapping("/playlists/users/{userId}")
    fun getPlaylistForUser(@PathVariable userId: Long): ResponseEntity<Playlist> =
            ResponseEntity.ok(playlistService.getPlaylistByUserId(userId))

}