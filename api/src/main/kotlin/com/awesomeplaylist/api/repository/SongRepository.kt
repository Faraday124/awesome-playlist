package com.awesomeplaylist.api.repository

import com.awesomeplaylist.api.model.Song
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s " +
            "JOIN PlaylistSong ps ON s.id = ps.song.id " +
            "WHERE ps.playlist.id = :playlistId")
    fun findSongsByPlaylistId(playlistId: Long, sort: Sort?): List<Song>
}
