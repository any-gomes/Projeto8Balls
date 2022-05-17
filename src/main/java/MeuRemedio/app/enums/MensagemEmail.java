package MeuRemedio.app.enums;

public enum MensagemEmail {
        /*Assunto dos E-MAILs enviados*/
        CADASTRO_REALIZADO("Cadastro realizado"),
        RECUPERACAO_SENHA("Recuperação de senha"),

        REMEDIO_CADASTRADO("Remédio cadastrado"),

        //Corpo dos E-MAILs a serem desparadas
        CADASTRO_MENSAGEM(". Queremos agradecer por ter se registrado na plataforma Meu Remédio. \n Acesse a plataforma em:"),
        RECUPERACAO_MENSAGEM(". Vimos que perdeu a senha, mas não se preocupe, acesse o link para recuperar "),

        CADASTRO_REMEDIO("Olá, vimos que você cadastrou um novo remédio, fique atento e use conforme as indicações médicas");

        private String descricao;

    MensagemEmail(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
    @Override
    public String toString(){
        return getDescricao();
    }
}


