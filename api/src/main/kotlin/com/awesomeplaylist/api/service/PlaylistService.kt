package com.awesomeplaylist.api.service

import com.awesomeplaylist.api.model.Playlist
import com.awesomeplaylist.api.repository.PlaylistRepository
import com.awesomeplaylist.api.repository.SongRepository
import org.springframework.stereotype.Service

@Service
class PlaylistService(
        private val playlistRepository: PlaylistRepository,
        private val songRepository: SongRepository
) {

    fun getPlaylistById(playlistId: Long, sortParam: String?, sortDirection: String?): Playlist =
            playlistRepository.findById(playlistId).orElseThrow()

    fun getPlaylistByUserId(userId: Long): Playlist =
            playlistRepository.findByUserId(userId)


    fun addSongToPlaylist(playlistId: Long, songId: Long) {
        val playlist = playlistRepository.findById(playlistId).orElseThrow()
        val song = songRepository.findById(songId).orElseThrow()
        playlist.songs += song
        playlistRepository.save(playlist)
    }

    fun removeSongFromPlaylist(playlistId: Long, songId: Long) {
        val playlist = playlistRepository.findById(playlistId).orElseThrow()
        val song = songRepository.findById(songId).orElseThrow()
        playlist.songs -= song
        playlistRepository.save(playlist)
    }
}
