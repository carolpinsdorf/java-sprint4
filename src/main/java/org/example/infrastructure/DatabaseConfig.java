package org.example.infrastructure;

import org.example.entities.Acesso;
import org.example.repositories.AcessoRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseConfig {
    private static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static String USER = "RM555130";
    private static String SENHA = "040506";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,SENHA);
    }
    public static void createDropTables(){
        String dropTables = """
            BEGIN
                EXECUTE IMMEDIATE 'DROP TABLE T_DIAGNOSTICO';
                EXECUTE IMMEDIATE 'DROP TABLE T_SERVICO';
                EXECUTE IMMEDIATE 'DROP TABLE T_ORDEM_SERVICO';
                EXECUTE IMMEDIATE 'DROP TABLE T_DTC';
                EXECUTE IMMEDIATE 'DROP TABLE T_ENDERECO';
                EXECUTE IMMEDIATE 'DROP TABLE T_AGENDAMENTO';
                EXECUTE IMMEDIATE 'DROP TABLE T_CARRO';
                EXECUTE IMMEDIATE 'DROP TABLE T_OFICINA';
                EXECUTE IMMEDIATE 'DROP TABLE T_CLIENTE';
                EXECUTE IMMEDIATE 'DROP TABLE T_ACESSO';
            EXCEPTION
                WHEN OTHERS THEN
                    IF SQLCODE != -942 THEN -- Ignorar erro de tabela inexistente
                        RAISE;
                    END IF;
            END;
            """;

        String createTableAcesso = """
                CREATE TABLE T_ACESSO (
                    id     int GENERATED AS IDENTITY PRIMARY KEY,
                    email_acesso  VARCHAR2(100) CONSTRAINT nn_email NOT NULL,
                    username      VARCHAR2(10) CONSTRAINT nn_username NOT NULL,
                    senha         VARCHAR2(10) CONSTRAINT nn_senha NOT NULL,
                    situacao      VARCHAR(10),
                    data_cadastro DATE,
                    CONSTRAINT uq_email UNIQUE (email_acesso),
                    CONSTRAINT uq_username UNIQUE (username),
                    CONSTRAINT chk_username CHECK (LENGTH(username)>=12),
                    CONSTRAINT chk_senha CHECK (LENGTH(senha)>10),
                    CONSTRAINT chk_email CHECK(email_acesso LIKE ('%_@__%.__%')),
                    CONSTRAINT chk_situacao CHECK (situacao IN ('ativo', 'desativado'))
                );
                """;

        String createTableCliente = """
                CREATE TABLE T_CLIENTE (
                    id      int GENERATED AS IDENTITY PRIMARY KEY,
                    cpf_cliente     int CONSTRAINT nn_cpf NOT NULL,
                    nm_cliente      VARCHAR(100) CONSTRAINT nn_nome NOT NULL,
                    rg_cliente      VARCHAR(10) ,
                    dt_nascimento   DATE CONSTRAINT nn_dt_nascimento NOT NULL,
                    sx_cliente      VARCHAR2(20),
                    estado_civil    VARCHAR2(20),
                    fk_acesso       int REFERENCES T_ACESSO(id_acesso),
                    CONSTRAINT uq_cpf_cliente UNIQUE (cpf_cliente),
                    CONSTRAINT chk_cpf CHECK (LENGTH(TO_CHAR(cpf_cliente)) = 11),
                    CONSTRAINT chk_sx_cliente CHECK (sx_cliente IN ('Masculino', 'Feminino', 'Outro')),
                    CONSTRAINT chk_estado_civil CHECK (estado_civil IN ('Solteiro', 'Casado', 'Divorciado', 'Viúvo'))
                );
                
                """;

        String createTableOficina = """
                CREATE TABLE T_OFICINA (
                    id      int GENERATED AS IDENTITY PRIMARY KEY,
                    cnpj_oficina    int CONSTRAINT nn_cnpj NOT NULL,
                    fk_acesso       int REFERENCES T_ACESSO(id_acesso),
                    CONSTRAINT  uq_cnpj UNIQUE(cnpj_oficina),
                    CONSTRAINT chk_cnpj CHECK (LENGTH(TO_CHAR(cnpj_oficina)) = 14)
                );
                """;

        String createTableCarro = """
                CREATE TABLE T_CARRO (
                    id       int GENERATED AS IDENTITY PRIMARY KEY,
                    placa          VARCHAR2(7) CONSTRAINT nn_placa NOT NULL,
                    modelo         VARCHAR2(50) CONSTRAINT nn_modelo NOT NULL,
                    marca          VARCHAR2(50) CONSTRAINT nn_marca NOT NULL,
                    ano_fabricacao int CONSTRAINT nn_ano_fab NOT NULL,
                    torque         int,
                    cambio         VARCHAR(11),
                    combustivel    VARCHAR(20),
                    cor            VARCHAR(20),
                    quilometragem  INTEGER,
                    fk_cliente     int REFERENCES T_CLIENTE(id_cliente),
                    CONSTRAINT uq_placa UNIQUE (placa),
                    CONSTRAINT chk_cambio CHECK (cambio IN ('automático', 'manual')),
                    CONSTRAINT chk_combustivel CHECK(combustivel IN ('gasolina', 'diesel', 'flex', 'elétrico'))
                );
                """;

        String createTableAgendamento = """
                CREATE TABLE T_AGENDAMENTO (
                    id     int GENERATED AS IDENTITY PRIMARY KEY,
                    dthora_agendamento DATE CONSTRAINT nn_dthora NOT NULL,
                    status_agendamento VARCHAR(10) CONSTRAINT nn_status_agendamento NOT NULL,
                    obs_agendamento    LONG,
                    fk_oficina         int REFERENCES T_OFICINA(id_oficina),
                    fk_carro           int REFERENCES T_CARRO(id_carro),
                    CONSTRAINT chk_st_agendamento CHECK (status_agendamento IN ('confirmado', 'cancelado'))
                );
                """;

        String createTableEndereco = """
                CREATE TABLE T_ENDERECO (
                    id    int GENERATED AS IDENTITY PRIMARY KEY,
                    cep_endereco   int CONSTRAINT nn_cep NOT NULL,
                    log_endereco   VARCHAR2(200) CONSTRAINT nn_log_endereco NOT NULL,
                    num_endereco   int CONSTRAINT nn_num_endereco NOT NULL,
                    cmpl_endereco  VARCHAR2(50),
                    bairro         VARCHAR2(100) CONSTRAINT nn_bairro NOT NULL,
                    cidade         VARCHAR2(100) CONSTRAINT nn_cidade NOT NULL,
                    estado         VARCHAR2(2) CONSTRAINT nn_estado NOT NULL,
                    fk_cliente     int REFERENCES T_CLIENTE(id_cliente),
                    fk_oficina     int REFERENCES T_OFICINA(id_oficina),
                    CONSTRAINT uq_cep UNIQUE(cep_endereco)
                );
                """;

        String createTableDtc = """
                CREATE TABLE T_DTC (
                    id    int GENERATED AS IDENTITY PRIMARY KEY,
                    cod_dtc   VARCHAR2(5) NOT NULL,
                    descricao LONG CONSTRAINT nn_desc_dtc NOT NULL
                );
                """;

        String createTableOrdemServico = """
                CREATE TABLE T_ORDEM_SERVICO (
                    id int GENERATED AS IDENTITY PRIMARY KEY,
                    status_servico   VARCHAR2(20) CONSTRAINT nn_st_servico NOT NULL,
                    fk_agendamento   int REFERENCES T_AGENDAMENTO(id_agendamento),
                    CONSTRAINT chk_st_servico CHECK (status_servico IN ('Concluído', 'Em andamento', 'Atrasado', 'Finalizado'))
                );
                """;
        String createTableServico = """
                CREATE TABLE T_SERVICO (
                    id                   int GENERATED AS IDENTITY PRIMARY KEY,
                    descricao                    LONG CONSTRAINT nn_desc_servico NOT NULL,
                    valor                        FLOAT CONSTRAINT nn_valor NOT NULL,
                    tempo_execucao               VARCHAR2(20) CONSTRAINT nn_tempo NOT NULL,
                    fk_agendamento               int REFERENCES T_AGENDAMENTO(id_agendamento)
                );
                """;
        String createTableDiagnostico = """
                CREATE TABLE T_DIAGNOSTICO (
                    id      int GENERATED AS IDENTITY PRIMARY KEY,
                    desc_diagnostico    LONG CONSTRAINT nn_desc_diagnostico NOT NULL,
                    fk_servico          int REFERENCES T_SERVICO(id_servico),
                    fk_id_dtc           int REFERENCES T_DTC(id_dtc)
                );
                """;
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Executar os comandos SQL para dropar as tabelas
            stmt.execute(dropTables);

            // Criar todas as tabelas
            stmt.execute(createTableAcesso);
            stmt.execute(createTableCliente);
            stmt.execute(createTableOficina);
            stmt.execute(createTableCarro);
            stmt.execute(createTableAgendamento);
            stmt.execute(createTableEndereco);
            stmt.execute(createTableDtc);
            stmt.execute(createTableOrdemServico);
            stmt.execute(createTableServico);
            stmt.execute(createTableDiagnostico);

            testAcessoRepo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testAcessoRepo() {
        AcessoRepo acessoRepo = new AcessoRepo();

        Acesso novoAcesso = new Acesso("teste@example.com", "usuario123456", "senha12345");
        acessoRepo.save(novoAcesso);
        System.out.println("Acesso inserido.");

        // Teste de busca por ID
        Acesso acessoEncontrado = acessoRepo.findById(1); // Supondo que o ID 1 exista
        if (acessoEncontrado != null) {
            System.out.println("Acesso encontrado: " + acessoEncontrado.getEmailAcesso());
        } else {
            System.out.println("Acesso não encontrado.");
        }

        // Teste de busca de todos os acessos
        List<Acesso> todosAcessos = acessoRepo.findAll();
        System.out.println("Total de acessos encontrados: " + todosAcessos.size());
        for (Acesso acesso : todosAcessos) {
            System.out.println("Acesso: " + acesso.getEmailAcesso());
        }

        // Teste de atualização
        if (acessoEncontrado != null) {
            acessoEncontrado.setSenha("novaSenha12345");
            acessoRepo.update(acessoEncontrado);
            System.out.println("Acesso atualizado.");
        }

        // Teste de exclusão
        if (acessoEncontrado != null) {
            acessoRepo.delete(acessoEncontrado.getId());
            System.out.println("Acesso deletado.");
        }
    }
}