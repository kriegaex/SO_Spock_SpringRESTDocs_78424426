package org.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [Application])
class MyControllerSpec extends Specification {
  def restDocumentation = new ManualRestDocumentation()
  @Autowired
  WebApplicationContext context
  MockMvc mockMvc

  def setup() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(documentationConfiguration(restDocumentation))
      .build()
    restDocumentation.beforeTest(getClass(), specificationContext.currentFeature.displayName)
  }

  def cleanup() {
    restDocumentation.afterTest()
  }

  def "should document the GET /hello endpoint"() {
    expect:
    mockMvc
      .perform(get("/"))
      .andExpect(status().isOk())
      .andDo(document("sample"))
  }
}
