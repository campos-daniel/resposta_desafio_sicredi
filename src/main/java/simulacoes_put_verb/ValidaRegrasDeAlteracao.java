package simulacoes_put_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.JUnitParamsRunner;



//TODO: Criar método para extrair para Objeto uma simulacao da API e por fim altera-lo
@RunWith(JUnitParamsRunner.class)
public class ValidaRegrasDeAlteracao {
	
	
	

	static String cpfAlvoAlteracao;
	static String endPoint;
			
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes/";
		cpfAlvoAlteracao = "66414919004";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	@Test
	public void verificaAlteracaoFormatoEmail() {

		given()
			.contentType("application/json")
			.body("{ \"email\": \"email@invalido\" }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.email", Matchers.is("E-mail deve ser um e-mail válido"))
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verficaAlteracaoFormatoCpf() {
		given()
			.contentType("application/json")
			.body("{ \"cpf\": \"075.575.285-80\" }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(400)
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verficaAlteracaoQuantidadeParcelas() {	
		given()
			.contentType("application/json")
			.body("{ \"parcelas\": 1 }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.parcelas", Matchers.is("Parcelas deve ser igual ou maior que 2"))
			.body("id", Matchers.nullValue())
		;
	}


	@Test
	public void verificaAlteracaoValor() {	
		given()
			.contentType("application/json")
			.body("{ \"valor\": 40000.01 }")
		.when()
			.post(endPoint)
		.then()
			.log().all()
			.statusCode(400)
			.body("erros.valor", Matchers.is("Valor deve ser menor ou igual a R$ 40.000"))
			.body("id", Matchers.nullValue())
		;
	}
	
}
