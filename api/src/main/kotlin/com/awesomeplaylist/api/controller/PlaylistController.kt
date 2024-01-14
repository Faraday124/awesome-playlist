package com.awesomeplaylist.api.controller

import com.awesomeplaylist.api.model.Playlist
import com.awesomeplaylist.api.model.Song
import com.awesomeplaylist.api.service.PlaylistService
import com.awesomeplaylist.api.service.SongsService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:5173", "http://localhost:3000"])
class PlaylistController(val playlistService: PlaylistService) {

    @PutMapping("/playlists/{playlistId}/songs/{songId}")
    fun addSongToPlaylist(@PathVariable playlistId: Long, @PathVariable songId: Long) = ResponseEntity.ok(playlistService.addSongToPlaylist(playlistId, songId))

    @DeleteMapping("/playlists/{playlistId}/songs/{songId}")
    fun deleteSongFromPlaylist(@PathVariable playlistId: Long, @PathVariable songId: Long) = ResponseEntity.ok(playlistService.removeSongFromPlaylist(playlistId, songId))

    @GetMapping("/users/{userId}/playlists")
    fun getPlaylistForUser(@PathVariable userId: Long): ResponseEntity<Playlist> =
            ResponseEntity.ok(playlistService.getPlaylistByUserId(userId))

}