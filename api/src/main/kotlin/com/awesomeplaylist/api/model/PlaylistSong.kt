package com.awesomeplaylist.api.model

import jakarta.persistence.*

@Entity
@Table(name = "playlist_song",  uniqueConstraints = [
     UniqueConstraint(name = "unique_column_name_constraint", columnNames = ["playlist_id", "song_id"])
])
data class PlaylistSong(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

        @ManyToOne
    @JoinColumn(name = "playlist_id")
    var playlist: Playlist,

        @ManyToOne
    @JoinColumn(name = "song_id")
    var song: Song,

//    @Column(name = "position")
//    var position: Int
)
