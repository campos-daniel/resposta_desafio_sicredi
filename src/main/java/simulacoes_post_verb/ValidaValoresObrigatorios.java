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
public class ValidaValoresObrigatorios {

	//TODO: Evitar parâmetros hard-code
	User simulacaoValoresCompletos = new User("01574526574", "Maria", "maria@gmail.com", 1358.43, 3, true);
	
	static String endPoint;
	
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	

	@Test
	public void verificaCampoObrigatorioParcela() {

		simulacaoValoresCompletos.setParcelas(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.parcelas", Matchers.is("Parcelas não pode ser vazio"))
			.body("id", Matchers.nullValue())
		;
	}
	
	@Test
	public void verificaCampoObrigatorioValor() {

		simulacaoValoresCompletos.setValor(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.valor", Matchers.is("Valor não pode ser vazio"))
			.body("id", Matchers.nullValue())
		;
	}
	
	
	@Test
	public void verificaCampoObrigatorioEmail() {

		simulacaoValoresCompletos.setEmail(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.email", Matchers.is("E-mail não deve ser vazio"))
		;
	}
	
	@Test
	public void verificaCampoObrigatorioNome() {

		simulacaoValoresCompletos.setNome(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.nome", Matchers.is("Nome não pode ser vazio"))
		;
	}
	
	@Test
	public void verificaCampoObrigatorioCpf() {

		simulacaoValoresCompletos.setCpf(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.cpf", Matchers.is("CPF não pode ser vazio"))
		;
	}
	
	@Test
	public void verificaCampoObrigatorioSeguro() {

		simulacaoValoresCompletos.setSeguro(null);
		given()
			.contentType("application/json")
			.body(simulacaoValoresCompletos)
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.seguro", Matchers.is("Seguro não pode ser vazio"))
		;
	}
	
	
}
