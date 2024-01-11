package com.awesomeplaylist.api.rest.model

import jakarta.persistence.*

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

    //@OneToMany(mappedBy = "playlist", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "playlist_song",
        joinColumns = [JoinColumn(name = "playlist_id")],
        inverseJoinColumns = [JoinColumn(name = "song_id")]
    )
    var songs: List<Song> = emptyList()
)
