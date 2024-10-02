package org.example.entitities;

public class Acesso extends _EntidadeBase{
    private String username;
    private String password;

    public Acesso() {
    }

    public Acesso(Long id) {
        super(id);
    }

    public Acesso(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Acesso(Long id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void alterarSenha(String novaSenha) {
        this.password = novaSenha;
    }

    @Override
    public String toString() {
        return "Acesso [Username=" + username + ", Password=" + password + "]";
    }

    public boolean verificarUsuario(String usuario) {
        return this.username.equals(usuario);
    }

    public void redefinirSenha() {
        this.password = "default";
    }
}

