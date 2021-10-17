package songlist.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import songlist.repository.features.DanceRepository;
import songlist.repository.features.RhythmRepository;
import songlist.service.features.RegionService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AbstractService {

    @MockBean
    public RhythmRepository rhythmRepository;

    @MockBean
    public DanceRepository danceRepository;

    @MockBean
    public RegionService regionService;
}
