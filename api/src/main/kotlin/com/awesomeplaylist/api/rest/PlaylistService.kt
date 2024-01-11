package com.awesomeplaylist.api.rest

import com.awesomeplaylist.api.rest.model.Playlist
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PlaylistService(
        private val playlistRepository: PlaylistRepository,
        private val songRepository: SongRepository
) {

    fun getPlaylistById(playlistId: Long, userId: Long, sortParam: String?, sortDirection: String?): Playlist =
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
