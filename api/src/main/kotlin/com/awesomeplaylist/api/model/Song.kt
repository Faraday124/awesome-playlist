package com.awesomeplaylist.api.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*


@Entity
@Table(name = "songs")
data class Song(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "title")
        var title: String,

        @Column(name = "artist")
        var artist: String,

        @Column(name = "album")
        var album: String,

        @Column(name = "year")
        var year: Long,

        @Column(name = "song_length")
        var songLength: String,

        @Column(name = "genre")
        var genre: String,

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        var createdAt: Date? = null
)