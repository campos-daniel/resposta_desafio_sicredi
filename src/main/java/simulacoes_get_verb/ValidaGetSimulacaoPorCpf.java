package simulacoes_get_verb;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import simulacoes_post_verb.User;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;

public class ValidaGetSimulacaoPorCpf {
	
	
	static String endPoint;
	static String cpfAlvoConsulta;
	@BeforeClass
	public static void configuracoes() {
		cpfAlvoConsulta = "66414919004";
		endPoint = "/api/v1/simulacoes/";
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
	}
	
	
	@Test
	public void verificaGetSimulacoesCpf() {

		User simulacaoConsultada = given()
		.when()
			.get(endPoint + cpfAlvoConsulta)
		.then()
			.log().all()
			.statusCode(200)
			.extract().body().as(User.class)
		;
		
		Assert.assertThat(simulacaoConsultada.getNome(), Matchers.is("Fulano"));
		Assert.assertThat(simulacaoConsultada.getCpf(), Matchers.is("66414919004"));
		Assert.assertThat(simulacaoConsultada.getEmail(), Matchers.is("fulano@gmail.com"));
		Assert.assertThat(simulacaoConsultada.getValor(), Matchers.is(11000.00));
		Assert.assertThat(simulacaoConsultada.getParcelas(), Matchers.is(3));
		Assert.assertThat(simulacaoConsultada.getSeguro(), Matchers.is(true));
	}
	
	@Test
	public void verificaGetSimulacoesCpfInexistente() {
				given()
				.when()
					.get(endPoint + "54787465758")
				.then()
					.log().all()
					.statusCode(404)
					.body("mensagem", Matchers.is("CPF 54787465758 não encontrado"))
				;
	}
}
