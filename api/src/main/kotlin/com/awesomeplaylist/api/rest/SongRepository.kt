package com.awesomeplaylist.api.rest

import com.awesomeplaylist.api.rest.model.Song
import org.hibernate.query.SortDirection
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
