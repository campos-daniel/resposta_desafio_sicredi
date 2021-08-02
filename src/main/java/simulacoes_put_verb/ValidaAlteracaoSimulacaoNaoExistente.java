package simulacoes_put_verb;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidaAlteracaoSimulacaoNaoExistente {
	
	static String cpfAlvoAlteracao;
	static String endPoint;
			
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes/";
		cpfAlvoAlteracao = "55555555555";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
	}
	
	@Test
	public void verificaSucessoAlteracaoNome() {	
		given()
			.contentType("application/json")
			.body("{ \"seguro\": true }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(404)
			.body("mensagem", Matchers.is("CPF 55555555555 não encontrado"))
		;
	}

}
