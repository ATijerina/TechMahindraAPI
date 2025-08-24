package base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    public static RequestSpecification withBody(RequestSpecification spec, Object body) {
        return spec.contentType(ContentType.JSON)
                .body(body);
    }
}
