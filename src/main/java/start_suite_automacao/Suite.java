package start_suite_automacao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import restricoes_cpf.*;
import simulacoes_delete_verb.ValidaExclusaoSimulacao;
import simulacoes_get_verb.ValidaGetListaSimulacoes;
import simulacoes_get_verb.ValidaGetSimulacaoPorCpf;
import simulacoes_post_verb.*;
import simulacoes_put_verb.ValidaAlteracaoSimulacaoNaoExistente;
import simulacoes_put_verb.ValidaRegrasDeAlteracao;
import simulacoes_put_verb.ValidaSucessoAlteracaoSimulacao;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ValidaValoresObrigatorios.class,
	ValidaRestricaoCpf.class,
	ValidaRegrasDeNegocio.class,
	ValidaCpfExistente.class,
	ValidaSucessoPostSimulacao.class,
	ValidaRegrasDeAlteracao.class,
	ValidaSucessoAlteracaoSimulacao.class,
	ValidaAlteracaoSimulacaoNaoExistente.class,
	ValidaGetListaSimulacoes.class,
	ValidaExclusaoSimulacao.class,
	ValidaGetSimulacaoPorCpf.class
})


public class Suite {
	
	

}
