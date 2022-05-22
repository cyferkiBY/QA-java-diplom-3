package api;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
@Builder
public class User {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String AUTHORISATION_PATH = "api/auth/";
    private static final Faker FAKER_RU = new Faker(new Locale("ru-RU"));
    private static final Faker FAKER_EN = new Faker(new Locale("en-GB"));
    private String email;
    private String password;
    private String name;

    public static User getRandomUserValidData() {
        final String email = getRandomValidEmail();
        final String password = getRandomValidPassword();
        final String name = getRandomValidName();
        return new User(email, password, name);
    }

    @Step("Get valid random name")
    public static String getRandomValidName() {
        return FAKER_RU.name().fullName();
    }

    @Step("Get valid random email")
    public static String getRandomValidEmail() {
        return FAKER_EN.internet().emailAddress();
    }

    @Step("Get valid random password")
    public static String getRandomValidPassword() {
        int minSizePassword = 6;
        int maxSizePassword = 20;
        return FAKER_EN.internet().password(minSizePassword, maxSizePassword);
    }

    @Step("Get too short random password")
    public static String getRandomTooShotPassword() {
        int minSizePassword = 1;
        int maxSizePassword = 6;
        return FAKER_EN.internet().password(minSizePassword, maxSizePassword);
    }

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Get user token using the API")
    private String getUserTokenUsingAPI() {
        String accessToken = "";
        ValidatableResponse response = given()
                .spec(getBaseSpec())
                .body(this)
                .when()
                .post(AUTHORISATION_PATH + "login")
                .then();
        if (response.extract().statusCode() == 200) {
            accessToken = response.extract().path("accessToken");
        }
        return accessToken;
    }

    @Step("Delete user using the API")
    public void deleteUserUsingAPI() {
        String token = getUserTokenUsingAPI();
        given()
                .spec(getBaseSpec())
                .header("authorization", token)
                .when()
                .delete(AUTHORISATION_PATH + "user")
                .then();
    }
}