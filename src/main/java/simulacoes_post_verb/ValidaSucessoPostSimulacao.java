package simulacoes_post_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;


@RunWith(JUnitParamsRunner.class)
public class ValidaSucessoPostSimulacao {
	
	//TODO: Evitar parâmetros hard-code
		User simulacaoValoresCompletos = new User("457894417453", "Sucesso Post Da Silva", "sucesso@gmail.com", 1500.30, 5, false);
		
		static String endPoint;
		
		@BeforeClass
		public static void configuracoes() {
			endPoint = "/api/v1/simulacoes";
			RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
			reqBuilder.log(LogDetail.ALL);
			requestSpecification = reqBuilder.build();
		}
		
		

		@Test
		public void verificaSucessoPostSimulacao() {

			given()
				.contentType("application/json")
				.body(simulacaoValoresCompletos)
			.when()
				.post(endPoint)
			.then()
				.log().all()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("nome", Matchers.is(simulacaoValoresCompletos.getNome()))
				.body("cpf", Matchers.is(simulacaoValoresCompletos.getCpf()))
				//TODO: Ajustar validação do valor
				.body("valor", Matchers.notNullValue())
				.body("parcelas", Matchers.is(simulacaoValoresCompletos.getParcelas()))
				.body("seguro", Matchers.is(simulacaoValoresCompletos.getSeguro()))
			;
		}

}
