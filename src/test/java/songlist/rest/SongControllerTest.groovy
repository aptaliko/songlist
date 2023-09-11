package songlist.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import groovy.transform.CompileDynamic
import songlist.model.song.dto.SongDTO
import songlist.model.song.dto.SongSearchCriteria
import songlist.rest.song.SongController
import songlist.service.metrics.CustomMetricService
import songlist.service.song.SongService
import spock.lang.Specification

/**
 * This class contains tests for the SongController class.
 * These tests verify the behavior of the SongController's methods.
 */
@CompileDynamic
class SongControllerTest extends Specification {

    private SongController songController
    private SongService songService
    private CustomMetricService customMetricService

    private void setup() {
        songService = Mock()
        customMetricService = Mock()
        songController = new SongController(songService, customMetricService)
    }

    private void "should_get_all_songs"() {
      given:
        SongDTO dto = new SongDTO()
        List<SongDTO> dtos = List.of(dto)
        songService.getAllSongs() >> dtos

      when:
        ResponseEntity<List<SongDTO>> response = songController.getAllSongs()

      then:
        dtos
        HttpStatus.OK == response.statusCode
        1 * songService.getAllSongs()
    }

    private void "should_search_based_on_criteria"() {
      given:
        SongSearchCriteria songSearchCriteria = new SongSearchCriteria()
        SongDTO dto = new SongDTO()
        List<SongDTO> dtos = List.of(dto)
        songService.getSongsOfCriteria(songSearchCriteria) >> dtos

      when:
        ResponseEntity<List<SongDTO>> response = songController.getSongsBasedOnCriteria(songSearchCriteria)

      then:
        dtos
        HttpStatus.OK == response.statusCode
        1 * songService.getSongsOfCriteria(songSearchCriteria)
        1 * customMetricService.incrementEndpointCallCounter()
    }

    private void "should_get_song_by_id"() {
      given:
        String id = 'some_id'
        SongDTO dto = new SongDTO()
        songService.getDTO(id) >> dto

      when:
        ResponseEntity<SongDTO> response = songController.getSong(id)

      then:
        dto
        HttpStatus.OK == response.statusCode
        1 * songService.getDTO(id)
    }

}
