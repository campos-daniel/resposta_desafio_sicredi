package simulacoes_get_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidaGetListaSimulacoes {
	
	static String endPoint;
			
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	
	@Test
	public void verificaListaSimulacoes() {

		given()
		.when()
			.get(endPoint)
		.then()
			.log().all()
			.statusCode(200)
			.body("$", Matchers.instanceOf(ArrayList.class))
		;
	}
}
