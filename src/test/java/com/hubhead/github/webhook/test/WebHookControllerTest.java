package com.hubhead.github.webhook.test;

import com.hubhead.github.webhook.config.CoreConfig;
import com.hubhead.github.webhook.config.MVCConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 2:32 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CoreConfig.class, MVCConfig.class})

public class WebHookControllerTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void pushNotAllowed() throws Exception {
        pushEvent("rejected");
    }

    private void pushEvent(String value) throws Exception {
        this.mockMvc.perform(
                post("/events")
                        .content(pushCommitJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect("allowed".equals(value)?status().isCreated():status().isInternalServerError()).
                andExpect(jsonPath("$.status").value(value));
    }

    @Test
    public void pushAllowed() throws Exception {

        this.mockMvc.perform(
                post("/pushers")
                        .content(pusherJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        pushEvent("allowed");
    }

    private String pusherJSON() {
        return "{\n" +
                "      \"email\":\"lolwut@noway.biz\",\n" +
                "      \"name\":\"Garen Torikian\"\n" +
                "}";
    }

    public static String pushCommitJSON() {
        return "{\n" +
                "  \"ref\": \"refs/heads/InitialDatabaseModel\",\n" +
                "  \"after\": \"18208d37e33691e40242c96150365c17e8998b31\",\n" +
                "  \"before\": \"c9b52e9780fcfb0421a433afa4b78711e907a166\",\n" +
                "  \"created\": false,\n" +
                "  \"deleted\": false,\n" +
                "  \"forced\": false,\n" +
                "  \"compare\": \"https://github.com/HubHead-DEV/AHCloud/compare/c9b52e9780fc...18208d37e336\",\n" +
                "  \"commits\": [\n" +
                "    {\n" +
                "      \"id\": \"18208d37e33691e40242c96150365c17e8998b31\",\n" +
                "      \"distinct\": true,\n" +
                "      \"message\": \"Test push hook\",\n" +
                "      \"timestamp\": \"2014-04-22T15:58:03-04:00\",\n" +
                "      \"url\": \"https://github.com/HubHead-DEV/AHCloud/commit/18208d37e33691e40242c96150365c17e8998b31\",\n" +
                "      \"author\": {\n" +
                "        \"name\": \"ysokolovski\",\n" +
                "        \"email\": \"yuris@nrx.com\",\n" +
                "        \"username\": \"ysokolovski\"\n" +
                "      },\n" +
                "      \"committer\": {\n" +
                "        \"name\": \"ysokolovski\",\n" +
                "        \"email\": \"yuris@nrx.com\",\n" +
                "        \"username\": \"ysokolovski\"\n" +
                "      },\n" +
                "      \"added\": [\n" +
                "\n" +
                "      ],\n" +
                "      \"removed\": [\n" +
                "\n" +
                "      ],\n" +
                "      \"modified\": [\n" +
                "        \"Src/datamodel/src/test/java/com/hubhead/datamodel/type/test/ObjectTypeTest.java\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"head_commit\": {\n" +
                "    \"id\": \"18208d37e33691e40242c96150365c17e8998b31\",\n" +
                "    \"distinct\": true,\n" +
                "    \"message\": \"Test push hook\",\n" +
                "    \"timestamp\": \"2014-04-22T15:58:03-04:00\",\n" +
                "    \"url\": \"https://github.com/HubHead-DEV/AHCloud/commit/18208d37e33691e40242c96150365c17e8998b31\",\n" +
                "    \"author\": {\n" +
                "      \"name\": \"ysokolovski\",\n" +
                "      \"email\": \"yuris@nrx.com\",\n" +
                "      \"username\": \"ysokolovski\"\n" +
                "    },\n" +
                "    \"committer\": {\n" +
                "      \"name\": \"ysokolovski\",\n" +
                "      \"email\": \"yuris@nrx.com\",\n" +
                "      \"username\": \"ysokolovski\"\n" +
                "    },\n" +
                "    \"added\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"removed\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"modified\": [\n" +
                "      \"Src/datamodel/src/test/java/com/hubhead/datamodel/type/test/ObjectTypeTest.java\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"repository\": {\n" +
                "    \"id\": 18817849,\n" +
                "    \"name\": \"AHCloud\",\n" +
                "    \"url\": \"https://github.com/HubHead-DEV/AHCloud\",\n" +
                "    \"description\": \"Asset Hub Cloud\",\n" +
                "    \"watchers\": 0,\n" +
                "    \"stargazers\": 0,\n" +
                "    \"forks\": 0,\n" +
                "    \"fork\": false,\n" +
                "    \"size\": 0,\n" +
                "    \"owner\": {\n" +
                "      \"name\": \"HubHead-DEV\",\n" +
                "      \"email\": \"dwu@hubhead.com\"\n" +
                "    },\n" +
                "    \"private\": true,\n" +
                "    \"open_issues\": 0,\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"language\": \"Java\",\n" +
                "    \"created_at\": 1397600072,\n" +
                "    \"pushed_at\": 1398196687,\n" +
                "    \"master_branch\": \"develop\",\n" +
                "    \"organization\": \"HubHead-DEV\"\n" +
                "  },\n" +
                "  \"pusher\": {\n" +
                "    \"name\": \"ysokolovski\",\n" +
                "    \"email\": \"yuris@nrx.com\"\n" +
                "  }\n" +
                "}";
    }

    public static String pushEventJSON() {
        return "{\n" +
                "   \"after\":\"1481a2de7b2a7d02428ad93446ab166be7793fbb\",\n" +
                "   \"before\":\"17c497ccc7cca9c2f735aa07e9e3813060ce9a6a\",\n" +
                "   \"commits\":[\n" +
                "      {\n" +
                "         \"added\":[\n" +
                " \n" +
                "         ],\n" +
                "         \"author\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"committer\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"distinct\":true,\n" +
                "         \"id\":\"c441029cf673f84c8b7db52d0a5944ee5c52ff89\",\n" +
                "         \"message\":\"Test\",\n" +
                "         \"modified\":[\n" +
                "            \"README.md\"\n" +
                "         ],\n" +
                "         \"removed\":[\n" +
                " \n" +
                "         ],\n" +
                "         \"timestamp\":\"2013-02-22T13:50:07-08:00\",\n" +
                "         \"url\":\"https://github.com/octokitty/testing/commit/c441029cf673f84c8b7db52d0a5944ee5c52ff89\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"added\":[\n" +
                " \n" +
                "         ],\n" +
                "         \"author\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"committer\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"distinct\":true,\n" +
                "         \"id\":\"36c5f2243ed24de58284a96f2a643bed8c028658\",\n" +
                "         \"message\":\"This is me testing the windows client.\",\n" +
                "         \"modified\":[\n" +
                "            \"README.md\"\n" +
                "         ],\n" +
                "         \"removed\":[\n" +
                " \n" +
                "         ],\n" +
                "         \"timestamp\":\"2013-02-22T14:07:13-08:00\",\n" +
                "         \"url\":\"https://github.com/octokitty/testing/commit/36c5f2243ed24de58284a96f2a643bed8c028658\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"added\":[\n" +
                "            \"words/madame-bovary.txt\"\n" +
                "         ],\n" +
                "         \"author\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"committer\":{\n" +
                "            \"email\":\"lolwut@noway.biz\",\n" +
                "            \"name\":\"Garen Torikian\",\n" +
                "            \"username\":\"octokitty\"\n" +
                "         },\n" +
                "         \"distinct\":true,\n" +
                "         \"id\":\"1481a2de7b2a7d02428ad93446ab166be7793fbb\",\n" +
                "         \"message\":\"Rename madame-bovary.txt to words/madame-bovary.txt\",\n" +
                "         \"modified\":[\n" +
                " \n" +
                "         ],\n" +
                "         \"removed\":[\n" +
                "            \"madame-bovary.txt\"\n" +
                "         ],\n" +
                "         \"timestamp\":\"2013-03-12T08:14:29-07:00\",\n" +
                "         \"url\":\"https://github.com/octokitty/testing/commit/1481a2de7b2a7d02428ad93446ab166be7793fbb\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"compare\":\"https://github.com/octokitty/testing/compare/17c497ccc7cc...1481a2de7b2a\",\n" +
                "   \"created\":false,\n" +
                "   \"deleted\":false,\n" +
                "   \"forced\":false,\n" +
                "   \"head_commit\":{\n" +
                "      \"added\":[\n" +
                "         \"words/madame-bovary.txt\"\n" +
                "      ],\n" +
                "      \"author\":{\n" +
                "         \"email\":\"lolwut@noway.biz\",\n" +
                "         \"name\":\"Garen Torikian\",\n" +
                "         \"username\":\"octokitty\"\n" +
                "      },\n" +
                "      \"committer\":{\n" +
                "         \"email\":\"lolwut@noway.biz\",\n" +
                "         \"name\":\"Garen Torikian\",\n" +
                "         \"username\":\"octokitty\"\n" +
                "      },\n" +
                "      \"distinct\":true,\n" +
                "      \"id\":\"1481a2de7b2a7d02428ad93446ab166be7793fbb\",\n" +
                "      \"message\":\"Rename madame-bovary.txt to words/madame-bovary.txt\",\n" +
                "      \"modified\":[\n" +
                " \n" +
                "      ],\n" +
                "      \"removed\":[\n" +
                "         \"madame-bovary.txt\"\n" +
                "      ],\n" +
                "      \"timestamp\":\"2013-03-12T08:14:29-07:00\",\n" +
                "      \"url\":\"https://github.com/octokitty/testing/commit/1481a2de7b2a7d02428ad93446ab166be7793fbb\"\n" +
                "   },\n" +
                "   \"pusher\":{\n" +
                "      \"email\":\"lolwut@noway.biz\",\n" +
                "      \"name\":\"Garen Torikian\"\n" +
                "   },\n" +
                "   \"ref\":\"refs/heads/master\",\n" +
                "   \"repository\":{\n" +
                "      \"created_at\":1332977768,\n" +
                "      \"description\":\"\",\n" +
                "      \"fork\":false,\n" +
                "      \"forks\":0,\n" +
                "      \"has_downloads\":true,\n" +
                "      \"has_issues\":true,\n" +
                "      \"has_wiki\":true,\n" +
                "      \"homepage\":\"\",\n" +
                "      \"id\":3860742,\n" +
                "      \"language\":\"Ruby\",\n" +
                "      \"master_branch\":\"master\",\n" +
                "      \"name\":\"testing\",\n" +
                "      \"open_issues\":2,\n" +
                "      \"owner\":{\n" +
                "         \"email\":\"lolwut@noway.biz\",\n" +
                "         \"name\":\"octokitty\"\n" +
                "      },\n" +
                "      \"private\":false,\n" +
                "      \"pushed_at\":1363295520,\n" +
                "      \"size\":2156,\n" +
                "      \"stargazers\":1,\n" +
                "      \"url\":\"https://github.com/octokitty/testing\",\n" +
                "      \"watchers\":1\n" +
                "   }\n" +
                "}";
    }

}
