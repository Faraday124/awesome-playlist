package com.awesomeplaylist.api.integration

import com.awesomeplaylist.api.config.MysqlConfig
import com.awesomeplaylist.api.model.Song
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest(classes = [MysqlConfig::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = [MysqlConfig::class], initializers = [MysqlConfig.TestContainerInitializer::class])
@Testcontainers
class SongsIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return all songs`() {
        //expect
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].title").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$[0].artist").value("Queen"))
    }

    @Test
    fun `should return all songs sorted in descending order`() {
        //expect
        mockMvc.perform(get("/songs?sortParameter=title&sortDirection=DESC"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("What's Going On"))
                .andExpect(jsonPath("$[0].artist").value("Marvin Gaye"))
    }

    @Test
    fun `should return song details`() {
        //expect
        mockMvc.perform(get("/songs/1/details"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("artist").value("Queen"))
                .andExpect(jsonPath("album").value("A Night at the Opera"))
                .andExpect(jsonPath("year").value(1975))
                .andExpect(jsonPath("songLength").value("5:55"))
                .andExpect(jsonPath("genre").value("Rock"))
    }

    @Test
    fun `should add a new song`() {
        //given
        val mapper = ObjectMapper()
        val requestJson = createSongRequest(mapper)

        //when
        val result: MvcResult = mockMvc.perform(post("/songs")
                .contentType(MediaType(MediaType.APPLICATION_JSON)).content(requestJson))
                .andExpect(status().isOk)
                .andReturn()

        val createdSong: String = result.response.contentAsString
        val songId = mapper.readValue(createdSong, Song::class.java).id

        //then
        mockMvc.perform(get("/songs/{songId}/details", songId))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Test Song"))

    }

    @Test
    fun `should return sorted songs for given playlist`() {
        //expect
        mockMvc.perform(get("/playlists/1/songs?sortParameter=artist&sortDirection=DESC"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].title").value("Bohemian Rhapsody"))
                .andExpect(jsonPath("$.[0].artist").value("Queen"))
    }

    private fun createSongRequest(mapper: ObjectMapper): String {
        val song = Song(id = 0, artist = "Dummy", title = "Test Song", album = "xx", year = 1999, songLength = "2:59", genre = "pop")
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        val ow: ObjectWriter = mapper.writer().withDefaultPrettyPrinter()
        return ow.writeValueAsString(song)
    }
}