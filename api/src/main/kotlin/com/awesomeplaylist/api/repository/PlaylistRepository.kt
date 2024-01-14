package com.awesomeplaylist.api.repository

import com.awesomeplaylist.api.model.Playlist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository : JpaRepository<Playlist, Long> {
    fun findByUserId(userId: Long): Playlist
}
