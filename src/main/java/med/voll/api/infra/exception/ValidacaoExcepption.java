package med.voll.api.infra.exception;

public class ValidacaoExcepption extends RuntimeException {
    public ValidacaoExcepption(String mensagem) {
        super((mensagem));
    }
}
