package com.awesomeplaylist.api.integration

import com.awesomeplaylist.api.config.MysqlConfig
import com.awesomeplaylist.api.model.Song
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [MysqlConfig::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = [MysqlConfig::class], initializers = [MysqlConfig.TestContainerInitializer::class])
@Testcontainers
class PlaylistIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should add and remove existing songs from a playlist`() {
        //given
        mockMvc.perform(get("/users/1/playlists"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.songs", hasSize<Song>(2)))

        //when
        mockMvc.perform(put("/playlists/{playlistId}/songs/{songId}", 1, 2))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //then
        mockMvc.perform(get("/users/1/playlists"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.songs", hasSize<Song>(3)))

        //when
        mockMvc.perform(delete("/playlists/{playlistId}/songs/{songId}", 1, 2))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //then
        mockMvc.perform(get("/users/1/playlists"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.songs", hasSize<Song>(2)))
    }


    @Test
    fun `should return playlist for a user`() {
        //expect
        mockMvc.perform(get("/users/1/playlists"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playlistName").value("Awesome Playlist"))
                .andExpect(jsonPath("$.songs", hasSize<Song>(2)))

    }

}