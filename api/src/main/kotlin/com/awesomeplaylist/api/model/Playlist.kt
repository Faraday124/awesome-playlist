package com.awesomeplaylist.api.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "playlists")
data class Playlist(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "user_id")
        var userId: Long,

        @Column(name = "playlist_name")
        var playlistName: String,

        @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
        @JoinTable(
                name = "playlist_song",
                joinColumns = [JoinColumn(name = "playlist_id")],
                inverseJoinColumns = [JoinColumn(name = "song_id")]
        )
        var songs: List<Song> = emptyList(),

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        var createdAt: Date? = null
)
