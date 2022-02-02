package br.edu.ufra.vacinas.api.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessagesUtil {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema," +
            " tente novamente mais tarde, " + "e se problema persistir," +
            " entre em contato com o administrador do sistema.";

    public static final String MSG_DADOS_INVALIDOS = "Um ou mais campos estão inválidos. " +
            "Faça o preenchimento correto e tente novamente.";

    public static final String MSG_ENTIDADE_EM_USO = "Registro de código %d não pode ser removido, pois está em uso";

}
