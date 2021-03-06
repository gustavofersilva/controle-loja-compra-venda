package br.edu.utfpr.pb.oo24s.aula4.javafx.model;

import br.edu.utfpr.pb.oo24s.aula4.javafx.util.BooleanConverter;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findByEmailAndSenha",
            query = "from Usuario u "
            + " where u.email=:email AND u.senha=:senha"),
    @NamedQuery(name = "Usuario.findAll",
            query = "Select u from Usuario u")
})
public class Usuario implements AbstractModel, Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Usuario.findAll";
    public static final String FIND_BY_EMAIL_AND_SENHA
            = "Usuario.findByEmailAndSenha";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O campo 'nome' é obrigatório!")
    @Column(length = 100, nullable = false)
    private String nome;
    @NotEmpty(message = "O campo 'email' é obrigatório!")
    @Column(length = 100, nullable = false)
    private String email;
    @NotEmpty(message = "O campo 'senha' é obrigatório!")
    @Column(length = 512, nullable = false)
    private String senha;
    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'T'")
    private Boolean ativo;
 
    @OneToMany(mappedBy = "usuario", orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
