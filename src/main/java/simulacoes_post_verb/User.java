package simulacoes_post_verb;



//TODO: Colocar classe User em outro pacote ou tornar enum
public class User {
	
	
	
	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private Double valor;
	private Integer parcelas;
	private Boolean seguro;
	
	public User(String cpf, String nome, String email, Double valor, Integer parcela, Boolean seguro) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.valor = valor;
		this.parcelas = parcela;
		this.seguro = seguro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcela) {
		this.parcelas = parcela;
	}
	public Boolean getSeguro() {
		return seguro;
	}
	public void setSeguro(Boolean seguro) {
		this.seguro = seguro;
	}
	
	

}
