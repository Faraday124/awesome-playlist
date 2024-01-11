package com.awesomeplaylist.api.rest

import com.awesomeplaylist.api.rest.model.Playlist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository : JpaRepository<Playlist, Long> {
    fun findByUserId(userId: Long): Playlist
}
