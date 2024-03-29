package com.awesomeplaylist.api.service

import com.awesomeplaylist.api.model.Song
import com.awesomeplaylist.api.repository.SongRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class SongsService(private val songRepository: SongRepository) {

    fun getAllSongs(): List<Song> = songRepository.findAll()
    fun getAllSongsSorted(sortParameter: String?, sortDirection: String?): List<Song> {
        if (sortParameter == null || sortDirection == null) {
            return songRepository.findAll()
        }
        return songRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection), sortParameter))
    }

    fun getPlaylistSongsSorted(playlistId: Long, sortParameter: String?, sortDirection: String?): List<Song> {
        if (sortParameter == null || sortDirection == null) {
            return songRepository.findSongsByPlaylistId(playlistId, null)
        }
        return songRepository.findSongsByPlaylistId(playlistId, Sort.by(Sort.Direction.fromString(sortDirection), sortParameter))
    }

    fun getSongDetails(songId: Long): Song = songRepository.findById(songId).orElseThrow{NotFoundException("Song with id $songId not found")}
    fun addNewSong(song: Song) = songRepository.save(song)
}
