package simulacoes_post_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidaRegrasDeNegocio {
	
	//TODO: Evitar parâmetros hard-code
		User simulacaoValoresCompletos = new User("45698727416", "Rafael", "rafael@gmail.com", 1000.00, 3, false);
		static String endPoint;
		
		@BeforeClass
		public static void configuracoes() {
			endPoint = "/api/v1/simulacoes";
			RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
			reqBuilder.log(LogDetail.ALL);
			requestSpecification = reqBuilder.build();
		}
		
		
		
		@Test
		public void verificaFormatoEmail() {

			simulacaoValoresCompletos.setEmail("email@invalido");
			given()
				.contentType("application/json")
				.body(simulacaoValoresCompletos)
			.when()
				.post(endPoint)
			.then()
				.log().all()
				.statusCode(400)
				.body("erros.email", Matchers.is("E-mail deve ser um e-mail válido"))
				.body("id", Matchers.nullValue())
			;
		}
		
		@Test
		public void verficaFormatoCpf() {

			simulacaoValoresCompletos.setCpf("075.575.285-80");
			given()
				.contentType("application/json")
				.body(simulacaoValoresCompletos)
			.when()
				.post(endPoint)
			.then()
				.log().all()
				.statusCode(400)
				.body("id", Matchers.nullValue())
			;
		}
		

		
		@FileParameters("src/main/resources/simulacoes/valida_regras_de_negocio/parcelas_valida_regras_de_negocio.csv")
		@Test
		public void verficaQuantidadeParcelas(Integer parcela, String validaRespostaServidor) {

			simulacaoValoresCompletos.setParcelas(parcela);
			given()
				.contentType("application/json")
				.body(simulacaoValoresCompletos)
			.when()
				.post(endPoint)
			.then()
				.log().all()
				.statusCode(400)
				.body("erros.parcelas", Matchers.is(validaRespostaServidor))
				.body("id", Matchers.nullValue())
			;
		}
	
		@FileParameters("src/main/resources/simulacoes/valida_regras_de_negocio/valor_valida_regras_de_negocio.csv")
		@Test
		public void verificaValor(Double valor, String validaRespostaServidor) {

			simulacaoValoresCompletos.setValor(valor);
			given()
				.contentType("application/json")
				.body(simulacaoValoresCompletos)
			.when()
				.post(endPoint)
			.then()
				.log().all()
				.statusCode(400)
				.body("erros.valor", Matchers.is(validaRespostaServidor))
				.body("id", Matchers.nullValue())
			;
		}
		
	

}
