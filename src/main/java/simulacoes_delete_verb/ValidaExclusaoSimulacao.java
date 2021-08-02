package simulacoes_delete_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.JUnitParamsRunner;
import simulacoes_post_verb.User;

@RunWith(JUnitParamsRunner.class)
public class ValidaExclusaoSimulacao {
	
	static String endPoint;
	User simulacaoValoresCompletos = new User("789456874", "Massa de Dados para Exclusão Jorge", "paraexclusao@gmail.com", 1000.00, 3, false);
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes/";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	
	@Test
	public void verificaExclusaoSimulacao() {
		
	User usuarioParaExclusao = given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(User.class)
		;

		given()
		.when()
			.delete(endPoint + usuarioParaExclusao.getId())
		.then()
			.log().all()
			.statusCode(204)
			.body(Matchers.is("OK"))
		;
	}
	
	@Test
	public void verificaExclusaoSimulacaoInexistente() {
		

		given()
		.when()
			.delete(endPoint + 999)
		.then()
			.log().all()
			.statusCode(404)
			.body(Matchers.is("Simulacao Nao Encontrada"))
		;
	}
	
	
	
	

}
