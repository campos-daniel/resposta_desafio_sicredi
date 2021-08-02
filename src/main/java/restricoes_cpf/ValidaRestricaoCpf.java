package restricoes_cpf;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidaRestricaoCpf {
	
	static String endPoint;
	
	@BeforeClass
	public static void configs() {
		endPoint = "/api/v1/restricoes/";
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		requestSpecification = reqBuilder.build();
		
	}
	
	
	
	@Test
	@FileParameters("src/main/resources/cpf_restrito.csv")
	public void validaCpfRestrito(String cpf, String bodyEsperado) {
		given()
		.when()
			.get(endPoint + cpf)
		.then()
			.log().all()
			.statusCode(200)
			.body("mensagem", Matchers.is(bodyEsperado))
			
		;
	}
	
	@FileParameters("src/main/resources/cpf_naorestrito.csv")
	@Test
	public void validaCpfNaoRestrito(String cpf) {
		given()
		.when()
			.get(endPoint + cpf)
		.then()
			.log().all()
			.statusCode(204)
		;
	}
	
	

}
