package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserControllerStep {

    private final static String apiCreateUser = "/api/auth/register";
    private final static String apiDeleteUser = "/api/auth/user";
    private final static String apiLoginUser = "/api/auth/login";

    @Step("Регистрация пользователя через API")
    public static Response apiExecuteCreate(CreateUser createUser) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(createUser)
                        .when()
                        .post(apiCreateUser);
        return response;
    }

    @Step("Авторизация пользователя по логину через API")
    public static Response apiExecuteLogin(LoginUser loginUser) {
        return
                given()
                        .header("Content-type", "application/json")
                        .body(loginUser)
                        .when()
                        .post(apiLoginUser);
    }

    @Step("Получение токена пользователя через API")
    public static String getUserToken(LoginUser loginUser) {
        Response response = apiExecuteLogin(loginUser);
        String accessToken = response.jsonPath().get("accessToken");
        return accessToken.split(" ")[1]; // разбили строку на 2 значения, разделитель Пробел. Выбрали второе значение (токен)
    }

    @Step("Удаление пользователя по логину через API")
    public static Response apiExecuteDelete(LoginUser loginUser) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(getUserToken(loginUser))
                        .when()
                        .delete(apiDeleteUser);
        return response;
    }

    @Step("Удаление пользователя по токену через API")
    public static Response apiExecuteDelete(String token) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(token)
                        .when()
                        .delete(apiDeleteUser);
        return response;
    }

    @Step("Удаление пользователя по логину и паролю")
    public static Response apiDeleteUser(String email, String password) {
        LoginUser loginUser = new LoginUser(email, password);
        return apiExecuteDelete(loginUser);
    }
}
