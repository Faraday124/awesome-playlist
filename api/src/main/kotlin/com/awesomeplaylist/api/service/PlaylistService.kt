package com.awesomeplaylist.api.service

import com.awesomeplaylist.api.model.Playlist
import com.awesomeplaylist.api.repository.PlaylistRepository
import com.awesomeplaylist.api.repository.SongRepository
import mu.KotlinLogging
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class PlaylistService(
        private val playlistRepository: PlaylistRepository,
        private val songRepository: SongRepository
) {
    private val logger = KotlinLogging.logger {}

    fun getPlaylistById(playlistId: Long): Playlist =
            playlistRepository.findById(playlistId).orElseThrow { NotFoundException("Playlist with id $playlistId not found") }

    fun getPlaylistByUserId(userId: Long): Playlist =
            playlistRepository.findByUserId(userId)


    fun addSongToPlaylist(playlistId: Long, songId: Long) {
        val playlist = getPlaylistById(playlistId = playlistId)
        val song = songRepository.findById(songId).orElseThrow { NotFoundException("Song with id $songId not found") }
        playlist.songs += song
        try {
            playlistRepository.save(playlist)
        } catch (e: RuntimeException) {
            logger.error { "Failed to add a song with id $songId to playlist $playlistId" }
            throw AddingSongException("Failed to add a song with id $songId to playlist $playlistId")
        }
    }

    fun removeSongFromPlaylist(playlistId: Long, songId: Long) {
        val playlist = getPlaylistById(playlistId = playlistId)
        val song = songRepository.findById(songId).orElseThrow { NotFoundException("Song with id $songId not found") }
        playlist.songs -= song
        try {
            playlistRepository.save(playlist)
        } catch (e: RuntimeException) {
            logger.error { "Failed to remove a song with id $songId to playlist $playlistId" }
            throw RemovingSongException("Failed to remove a song with id $songId to playlist $playlistId")
        }
    }
}
