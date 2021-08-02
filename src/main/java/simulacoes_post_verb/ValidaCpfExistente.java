package simulacoes_post_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidaCpfExistente {
	
	User simulacaoValoresCompletos = new User("17822386034", "Rafael", "rafael@gmail.com", 1000.00, 3, false);
	static String endPoint;
	
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	//TODO: Fazer método criar a própria massa de dados, sem depender de CPF anterior
	@Test
	public void validaCpfDuplicado() {
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(409)
			.body("mensagem", Matchers.is("CPF duplicado"))
			.body("id", Matchers.nullValue())
		;
	}
}
