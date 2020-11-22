package com.bruno.ws.domain.enums;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String descricao;

    private Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // metodo do tipo static para que a operação se possivel de serem executada mesmo sem instanciar objetos
    public static Role toEnum(String descricao) {
        if(descricao == null) {
            return null;
        }

        // buscar todo objeto x nos valores possiveis do pagamento
        for(Role x : Role.values()) {
            // testar se o cod que veio no argumento comparar com metodo equal for igual com o qual estou procurando
            if(descricao.equals(x.getDescricao())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Descrição inválido: " + descricao);
    }
}
