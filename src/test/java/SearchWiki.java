import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchWiki {

    String uri = "/selenide/selenide";

    String textSearch = "@ExtendWith({SoftAssertsExtension.class})\n" +
                        "class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void fillForm() {
        open(uri);

        $("#wiki-tab").click();
        $$("ul.m-0 li").last().$("button").click();
        $$("ul.m-0 li").findBy(text("SoftAssertions")).$("span").click();
        $("div.markdown-body").shouldHave(text(textSearch));
    }

    @AfterAll
    static void tearDown(){
        Selenide.closeWebDriver();
    }

}
