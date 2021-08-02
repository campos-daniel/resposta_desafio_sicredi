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
public class ValidaSucessoAlteracaoSimulacao {
	
	static String cpfAlvoAlteracao;
	static String endPoint;
			
	@BeforeClass
	public static void configuracoes() {
		endPoint = "/api/v1/simulacoes/";
		cpfAlvoAlteracao = "17822386034";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	@Test
	public void verificaSucessoAlteracaoEmail() {

		given()
			.contentType("application/json")
			.body("{ \"email\": \"deltranoalterado@gmail.com\" }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			.body("erros.email", Matchers.is("deltranoalterado@gmail.com"))
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verficaSucessoAlteracaoCpf() {
		given()
			.contentType("application/json")
			.body("{ \"cpf\": \"17822386034\" }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			.body("cpf", Matchers.is("17822386034"))
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verficaSucessoAlteracaoQuantidadeParcelas() {	
		given()
			.contentType("application/json")
			.body("{ \"parcelas\": 8 }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			.body("erros.parcelas", Matchers.is(8))
			.body("id", Matchers.notNullValue())
		;
	}


	@Test
	public void verificaSucessoAlteracaoValor() {	
		given()
			.contentType("application/json")
			.body("{ \"valor\": 35000.00 }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			//TODO: Ajustar validacao tipo Double
			.body("erros.valor", Matchers.is(""))
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verificaSucessoAlteracaoNome() {	
		given()
			.contentType("application/json")
			.body("{ \"nome\": \"Deltrano Alterado\" }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			.body("erros.nome", Matchers.is("Deltrano Alterado"))
			.body("id", Matchers.notNullValue())
		;
	}
	
	@Test
	public void verificaSucessoAlteracaoSeguro() {	
		given()
			.contentType("application/json")
			.body("{ \"seguro\": false }")
		.when()
			.put(endPoint + cpfAlvoAlteracao)
		.then()
			.log().all()
			.statusCode(201)
			.body("erros.seguro", Matchers.is(false))
			.body("id", Matchers.notNullValue())
		;
	}

}
