package com.offershopper.searchservice.searchcontrollertest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.offershopper.searchservice.controller.SearchController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchController.class)
public class SearchControllerTest {
  
 /* @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CarryBagRepository carryBagRepository;
  @InjectMocks
  private CarryBagController carryBagController;

  @Before
  public void setUp() throws Exception {
    // System.out.println(jsonContent.toString());
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(carryBagController).build();
  }

*/
}
